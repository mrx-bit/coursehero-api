package kz.iitu.hackday.coursehero.service;


import kz.iitu.hackday.coursehero.entity.*;
import kz.iitu.hackday.coursehero.entity.enums.CategoryType;

import java.util.List;

public interface CatalogService {

    // Category services
    Category getCategoryById(Long id);
    Category createCategory(Category category);
    Category updateCategory(Long id, Category category);
    void deleteCategory(Long id);
    List<Category> getAllCategories(CategoryType type);
    List<Category> searchCategories(String searchString, CategoryType type);

    // Disability services
    Disability getDisabilityById(Long id);
    Disability createDisability(Disability disability);
    Disability updateDisability(Long id, Disability disability);
    void deleteDisability(Long id);
    List<Disability> getAllDisabilities();
    List<Disability> searchDisability(String searchString);

    // Interest services
    Interest getInterestById(Long id);
    Interest createInterest(Interest interest);
    Interest updateInterest(Long id, Interest interest);
    void deleteInterest(Long id);
    List<Interest> getAllInterests();
    List<Interest> searchInterest(String searchString);

    // Organization services
    Organization getOrganizationById(Long id);
    Organization createOrganization(Organization organization);
    Organization updateOrganization(Long id, Organization organization);
    void deleteOrganization(Long id);
    List<Organization> getAllOrganizations();
    List<Organization> searchOrganization(String searchString);

    // Pathology services
    Pathology getPathologyById(Long id);
    Pathology createPathology(Pathology pathology);
    Pathology updatePathology(Long id, Pathology pathology);
    void deletePathology(Long id);
    List<Pathology> getAllPathologies();
    List<Pathology> searchPathology(String searchString);

    // Tag services
    Tag getTagById(Long id);
    Tag createTag(Tag tag);
    Tag updateTag(Long id, Tag tag);
    void deleteTag(Long id);
    List<Tag> getAllTags();
    List<Tag> searchTag(String searchString);
}
