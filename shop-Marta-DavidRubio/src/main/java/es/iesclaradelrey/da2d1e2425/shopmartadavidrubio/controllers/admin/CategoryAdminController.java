package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.controllers.admin;


import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.admin.NewCategoryDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.exceptions.CategoryNotFoundException;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.exceptions.admin.CategoryAlreadyExistsException;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.exceptions.admin.CategoryNotEmptyException;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
                                 @RequestParam(defaultValue = "name") String orderBy,
                                 @RequestParam(defaultValue = "asc") String orderDir,
                                 Model model) {

        Map<String, String> orderFields = new LinkedHashMap<>();
        orderFields.put("name", "Nombre");
        orderFields.put("description", "Descripcion");
        orderFields.put("id", "Id");
        model.addAttribute("orderFields", orderFields);

        model.addAttribute("categories", categoryService.findAll(pageNumber, pageSize, orderBy, orderDir));
        model.addAttribute("orderBy", orderBy);
        model.addAttribute("orderDir", orderDir);
        model.addAttribute("pageSizeOptions", List.of(1, 2, 3));


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
        } catch (CategoryNotFoundException | CategoryNotEmptyException e) {
            redirectAttributes.addFlashAttribute("globalError", e.getMessage());
        }
        return "redirect:/admin/categories/list";
    }


    @GetMapping("/new")
    public ModelAndView newCategory() {
        ModelAndView modelAndView = new ModelAndView("admin/categories/forms/newCategoryForm");

        modelAndView.addObject("category", new NewCategoryDto());
        modelAndView.addObject("categories", categoryService.findAll());

        return modelAndView;
    }


    @PostMapping("/new")
    public String newCategory(@Valid @ModelAttribute("category") NewCategoryDto newCategory, BindingResult bindingResult, Model model,
                              RedirectAttributes redirectAttributes) {
        bindingResult.getAllErrors().forEach(error -> {
            System.out.println(error.getDefaultMessage());
        });
        model.addAttribute("categories", categoryService.findAll());
        if (bindingResult.hasErrors()) {

            return "admin/categories/forms/newCategoryForm";
        }

        try {
            if (new Random().nextInt(100) > 70) {
                throw new RuntimeException("Error aleaterio");
            }
            ;
            categoryService.create(newCategory);
        } catch (CategoryAlreadyExistsException e) {
            bindingResult.rejectValue("name", null, "Error al crear la categoría: " +
                    e.getMessage());

            return "admin/categories/forms/newCategoryForm";
        } catch (Exception e) {

            bindingResult.reject("globalError", "Error inesperado al crear la categoría: " + e.getMessage());

            return "admin/categories/forms/newCategoryForm";
        }

        redirectAttributes.addFlashAttribute("successMessage", "Categoría creada correctamente");


        return "redirect:/admin/categories/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            Category category = categoryService.findById(id)
                    .orElseThrow(() -> new CategoryNotFoundException("Categoría no encontrada"));


            NewCategoryDto categoryDto = new NewCategoryDto();
            categoryDto.setName(category.getName());
            categoryDto.setDescription(category.getDescription());

            model.addAttribute("category", categoryDto);
            model.addAttribute("categoryId", id);

        } catch (CategoryNotFoundException e) {

            model.addAttribute("globalError", e.getMessage());
            return "redirect:/admin/categories/list";
        }

        return "admin/categories/forms/editCategoryForm";
    }

    @PostMapping("/edit/{id}")
    public String updateCategory(
            @PathVariable Long id,
            @Valid @ModelAttribute("category") NewCategoryDto updatedCategory,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        bindingResult.getAllErrors().forEach(error -> {
            System.out.println(error.getDefaultMessage());
        });

        model.addAttribute("categoryId", id);
        if (bindingResult.hasErrors()) {

            return "admin/categories/forms/editCategoryForm";
        }

        try {
            if (new Random().nextInt(100) > 70) {
                throw new RuntimeException("Error aleaterio");
            }
            ;
            categoryService.update(id, updatedCategory);
            redirectAttributes.addFlashAttribute("successMessage", "Categoría actualizada correctamente");
            return "redirect:/admin/categories/list";

        } catch (CategoryNotFoundException e) {

            bindingResult.reject("globalError", e.getMessage());
        } catch (CategoryAlreadyExistsException e) {
            bindingResult.rejectValue("name", "category.name", e.getMessage());
        } catch (Exception e) {
            bindingResult.reject("globalError", "Error inesperado: " + e.getMessage());
        }


        return "admin/categories/forms/editCategoryForm";
    }
}


