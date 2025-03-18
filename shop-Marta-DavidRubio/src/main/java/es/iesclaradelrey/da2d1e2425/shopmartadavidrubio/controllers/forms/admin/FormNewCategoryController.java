package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.controllers.forms.admin;


import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.admin.NewCategoryDto;

@Controller
@RequestMapping("/forms")
public class FormNewCategoryController {

    private final CategoryService categoryService;

    public FormNewCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/newCategory")
    public ModelAndView newCategory() {
        ModelAndView modelAndView = new ModelAndView("admin/categories/forms/newCategoryForm");

        modelAndView.addObject("category", new NewCategoryDto());
        modelAndView.addObject("categories", categoryService.findAll());

        return modelAndView;
    }

    @PostMapping("/newCategory")
    public String newCategory(@Valid @ModelAttribute("category") NewCategoryDto newCategory, BindingResult bindingResult, Model model) {
        bindingResult.getAllErrors().forEach(error -> {
            System.out.println(error.getDefaultMessage());
        });
        System.out.println(newCategory);
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "admin/categories/forms/newCategoryForm";
        }

        model.addAttribute("categories", categoryService.findAll());
        categoryService.create(newCategory);
        return "admin/categories/forms/newCategoryForm";
    }
}
