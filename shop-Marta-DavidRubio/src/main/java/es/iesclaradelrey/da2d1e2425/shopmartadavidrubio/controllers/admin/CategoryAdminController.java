package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.controllers.admin;


import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/categories")
public class CategoryAdminController {

    private final CategoryService categoryService;


    public CategoryAdminController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String pageCategories(@RequestParam(defaultValue = "1") Integer pageNumber,
                        @RequestParam(defaultValue = "1") Integer pageSize,
                        @RequestParam(defaultValue = "name")String orderBy,
                        @RequestParam(defaultValue = "asc") String orderDir,
                        Model model) {

        Map<String,String> orderFields=new LinkedHashMap<>();
        orderFields.put("name", "Nombre");
        orderFields.put("description", "Descripcion");
        orderFields.put("id", "Id");
        model.addAttribute("orderFields", orderFields);

        model.addAttribute("categories", categoryService.findAll(pageNumber, pageSize, orderBy, orderDir));
        model.addAttribute("orderBy", orderBy);
        model.addAttribute("orderDir", orderDir);
        model.addAttribute("pageSizeOptions", List.of(1,2,3));


        return "admin/categories/list";
    }
    @GetMapping("/delete/{id}")
    public String showDeleteForm(@PathVariable Long id, Model model) {
        try {
            var category = categoryService.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
            model.addAttribute("category", category);
        } catch (Exception e) {
            model.addAttribute("globalError", "No se pudo cargar la categoría para eliminar.");
            return "redirect:/admin/categories/list";
        }
        return "admin/categories/forms/deleteCategoryForm";
    }



    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            categoryService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Categoría eliminada correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("globalError", "No se pudo eliminar la categoría.");

        }
        return "redirect:/admin/categories/list";
    }

}
