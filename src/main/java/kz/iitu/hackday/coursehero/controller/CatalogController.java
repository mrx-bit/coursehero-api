package kz.iitu.hackday.coursehero.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kz.iitu.hackday.coursehero.entity.*;
import kz.iitu.hackday.coursehero.entity.enums.CategoryType;
import kz.iitu.hackday.coursehero.service.CatalogService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/catalogs")
@AllArgsConstructor
public class CatalogController {

    private CatalogService catalogService;

    @GetMapping("/category/course")
    @ApiOperation(value = "Метод для получения списка всех категории курсов", response = ResponseEntity.class)
    public ResponseEntity<?> getAllCourseCategories() {
        return ResponseEntity.ok(catalogService.getAllCategories(CategoryType.COURSE_CATEGORY));
    }

    @GetMapping("/category/course/search")
    @ApiOperation(value = "Метод для поиска категории курсов", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchString", dataType = "string", value = "Строка для поиска", paramType = "query"),
    })
    public ResponseEntity<?> searchCourseCategories(@RequestParam String searchString) {
        return ResponseEntity.ok(catalogService.searchCategories(searchString, CategoryType.COURSE_CATEGORY));
    }

    @GetMapping("/category/event")
    @ApiOperation(value = "Метод для получения списка всех категории событии", response = ResponseEntity.class)
    public ResponseEntity<?> getAllEventCategories() {
        return ResponseEntity.ok(catalogService.getAllCategories(CategoryType.EVENT_CATEGORY));
    }

    @GetMapping("/category/event/search")
    @ApiOperation(value = "Метод для поиска категории событии", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchString", dataType = "string", value = "Строка для поиска", paramType = "query"),
    })
    public ResponseEntity<?> searchEventCategories(@RequestParam String searchString) {
        return ResponseEntity.ok(catalogService.searchCategories(searchString, CategoryType.EVENT_CATEGORY));
    }

    @GetMapping("/category/news")
    @ApiOperation(value = "Метод для получения списка всех категории новостей", response = ResponseEntity.class)
    public ResponseEntity<?> getAllNewsCategories() {
        return ResponseEntity.ok(catalogService.getAllCategories(CategoryType.NEWS_CATEGORY));
    }

    @GetMapping("/category/news/search")
    @ApiOperation(value = "Метод для поиска категории новостей", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchString", dataType = "string", value = "Строка для поиска", paramType = "query"),
    })
    public ResponseEntity<?> searchNewsCategories(@RequestParam String searchString) {
        return ResponseEntity.ok(catalogService.searchCategories(searchString, CategoryType.NEWS_CATEGORY));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(catalogService.getCategoryById(id));
    }

    @PostMapping("/category")
    public ResponseEntity<?> createCategory(@Valid @RequestBody Category category) {
        return ResponseEntity.ok(catalogService.createCategory(category));
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id,
                                    @Valid @RequestBody Category category) {
        return ResponseEntity.ok(catalogService.updateCategory(id, category));
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        catalogService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

    // Disabilities
    @GetMapping("/disability")
    public ResponseEntity<?> getAllDisabilities() {
        return ResponseEntity.ok(catalogService.getAllDisabilities());
    }

    @GetMapping("/disability/search")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchString", dataType = "string", value = "Строка для поиска", paramType = "query"),
    })
    public ResponseEntity<?> searchDisabilities(@RequestParam String searchString) {
        return ResponseEntity.ok(catalogService.searchDisability(searchString));
    }

    @GetMapping("/disability/{id}")
    public ResponseEntity<?> getDisabilityById(@PathVariable Long id) {
        return ResponseEntity.ok(catalogService.getDisabilityById(id));
    }

    @PostMapping("/disability")
    public ResponseEntity<?> createDisability(@Valid @RequestBody Disability disability) {
        return ResponseEntity.ok(catalogService.createDisability(disability));
    }

    @PutMapping("/disability/{id}")
    public ResponseEntity<?> updateDisability(@PathVariable Long id,
                                              @Valid @RequestBody Disability disability) {
        return ResponseEntity.ok(catalogService.updateDisability(id, disability));
    }

    @DeleteMapping("/disability/{id}")
    public ResponseEntity<?> deleteDisability(@PathVariable Long id) {
        catalogService.deleteDisability(id);
        return ResponseEntity.ok().build();
    }

    // Interests
    @GetMapping("/interest")
    public ResponseEntity<?> getAllInterests() {
        return ResponseEntity.ok(catalogService.getAllInterests());
    }

    @GetMapping("/interest/search")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchString", dataType = "string", value = "Строка для поиска", paramType = "query"),
    })
    public ResponseEntity<?> searchInterests(@RequestParam String searchString) {
        return ResponseEntity.ok(catalogService.searchInterest(searchString));
    }

    @GetMapping("/interest/{id}")
    public ResponseEntity<?> getInterestById(@PathVariable Long id) {
        return ResponseEntity.ok(catalogService.getInterestById(id));
    }

    @PostMapping("/interest")
    public ResponseEntity<?> createInterest(@Valid @RequestBody Interest interest) {
        return ResponseEntity.ok(catalogService.createInterest(interest));
    }

    @PutMapping("/interest/{id}")
    public ResponseEntity<?> updateInterest(@PathVariable Long id,
                                            @Valid @RequestBody Interest interest) {
        return ResponseEntity.ok(catalogService.updateInterest(id, interest));
    }

    @DeleteMapping("/interest/{id}")
    public ResponseEntity<?> deleteInterest(@PathVariable Long id) {
        catalogService.deleteInterest(id);
        return ResponseEntity.ok().build();
    }

    // Organizations
    @GetMapping("/organization")
    public ResponseEntity<?> getAllOrganizations() {
        return ResponseEntity.ok(catalogService.getAllOrganizations());
    }

    @GetMapping("/organization/search")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchString", dataType = "string", value = "Строка для поиска", paramType = "query"),
    })
    public ResponseEntity<?> searchOrganizations(@RequestParam String searchString) {
        return ResponseEntity.ok(catalogService.searchOrganization(searchString));
    }

    @GetMapping("/organization/{id}")
    public ResponseEntity<?> getOrganizationById(@PathVariable Long id) {
        return ResponseEntity.ok(catalogService.getOrganizationById(id));
    }

    @PostMapping("/organization")
    public ResponseEntity<?> createOrganization(@Valid @RequestBody Organization organization) {
        return ResponseEntity.ok(catalogService.createOrganization(organization));
    }

    @PutMapping("/organization/{id}")
    public ResponseEntity<?> updateOrganization(@PathVariable Long id,
                                                @Valid @RequestBody Organization organization) {
        return ResponseEntity.ok(catalogService.updateOrganization(id, organization));
    }

    @DeleteMapping("/organization/{id}")
    public ResponseEntity<?> deleteOrganization(@PathVariable Long id) {
        catalogService.deleteOrganization(id);
        return ResponseEntity.ok().build();
    }

    // Pathologies
    @GetMapping("/pathology")
    public ResponseEntity<?> getAllPathologies() {
        return ResponseEntity.ok(catalogService.getAllPathologies());
    }

    @GetMapping("/pathology/search")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchString", dataType = "string", value = "Строка для поиска", paramType = "query"),
    })
    public ResponseEntity<?> searchPathologies(@RequestParam String searchString) {
        return ResponseEntity.ok(catalogService.searchPathology(searchString));
    }

    @GetMapping("/pathology/{id}")
    public ResponseEntity<?> getPathologyById(@PathVariable Long id) {
        return ResponseEntity.ok(catalogService.getPathologyById(id));
    }

    @PostMapping("/pathology")
    public ResponseEntity<?> createPathology(@Valid @RequestBody Pathology pathology) {
        return ResponseEntity.ok(catalogService.createPathology(pathology));
    }

    @PutMapping("/pathology/{id}")
    public ResponseEntity<?> updatePathology(@PathVariable Long id,
                                             @Valid @RequestBody Pathology pathology) {
        return ResponseEntity.ok(catalogService.updatePathology(id, pathology));
    }

    @DeleteMapping("/pathology/{id}")
    public ResponseEntity<?> deletePathology(@PathVariable Long id) {
        catalogService.deletePathology(id);
        return ResponseEntity.ok().build();
    }

    // Tags
    @GetMapping("/tag")
    public ResponseEntity<?> getAllTags() {
        return ResponseEntity.ok(catalogService.getAllTags());
    }

    @GetMapping("/tag/search")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchString", dataType = "string", value = "Строка для поиска", paramType = "query"),
    })
    public ResponseEntity<?> searchTags(@RequestParam String searchString) {
        return ResponseEntity.ok(catalogService.searchTag(searchString));
    }

    @GetMapping("/tag/{id}")
    public ResponseEntity<?> getTagById(@PathVariable Long id) {
        return ResponseEntity.ok(catalogService.getTagById(id));
    }

    @PostMapping("/tag")
    public ResponseEntity<?> createTag(@Valid @RequestBody Tag tag) {
        return ResponseEntity.ok(catalogService.createTag(tag));
    }

    @PutMapping("/tag/{id}")
    public ResponseEntity<?> updateTag(@PathVariable Long id,
                                       @Valid @RequestBody Tag tag) {
        return ResponseEntity.ok(catalogService.updateTag(id, tag));
    }

    @DeleteMapping("/tag/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable Long id) {
        catalogService.deleteTag(id);
        return ResponseEntity.ok().build();
    }
}
