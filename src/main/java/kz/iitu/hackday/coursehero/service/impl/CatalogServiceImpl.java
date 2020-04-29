package kz.iitu.hackday.coursehero.service.impl;

import kz.iitu.hackday.coursehero.entity.*;
import kz.iitu.hackday.coursehero.entity.enums.CategoryType;
import kz.iitu.hackday.coursehero.repository.*;
import kz.iitu.hackday.coursehero.service.CatalogService;
import kz.iitu.hackday.coursehero.utils.constants.ErrorMessageConstants;
import kz.iitu.hackday.coursehero.utils.exceptions.NoContentFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private CategoryRepository categoryRepository;
    private DisabilityRepository disabilityRepository;
    private InterestRepository interestRepository;
    private OrganizationRepository organizationRepository;
    private PathologyRepository pathologyRepository;
    private TagRepository tagRepository;

    @Override
    public Category getCategoryById(Long id) {
        log.info("CatalogServiceImpl.getCategoryById id " + id);
        return categoryRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE));
    }

    @Override
    public Category createCategory(Category category) {
        category.setId(null);
        return categoryRepository.saveAndFlush(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        log.info(String.format("CatalogServiceImpl.updateCategory: %d. Category {}: %s", id, category));

        Category dbCategory = categoryRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        dbCategory.setName(category.getName());
        dbCategory.setParentId(category.getParentId());

        return categoryRepository.saveAndFlush(dbCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Category dbCategory = categoryRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );
        dbCategory.setIsActive(false);
        categoryRepository.saveAndFlush(dbCategory);
    }

    @Override
    public List<Category> getAllCategories(CategoryType type) {
        return categoryRepository.findAllByTypeAndIsActiveTrue(type);
    }

    @Override
    public List<Category> searchCategories(String searchString, CategoryType type) {
        return categoryRepository.search(searchString, type);
    }

    @Override
    public Disability getDisabilityById(Long id) {
        log.info("CatalogServiceImpl.getDisabilityById id " + id);
        return disabilityRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE));
    }

    @Override
    public Disability createDisability(Disability disability) {
        disability.setId(null);
        return disabilityRepository.saveAndFlush(disability);
    }

    @Override
    public Disability updateDisability(Long id, Disability disability) {
        log.info(String.format("CatalogServiceImpl.updateDisability: %d. Disability {}: %s", id, disability));

        Disability dbDisability = disabilityRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        dbDisability.setName(disability.getName());

        return disabilityRepository.saveAndFlush(dbDisability);
    }

    @Override
    public void deleteDisability(Long id) {
        Disability dbDisability = disabilityRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        dbDisability.setIsActive(false);

        disabilityRepository.saveAndFlush(dbDisability);
    }

    @Override
    public List<Disability> getAllDisabilities() {
        return disabilityRepository.findAllByIsActiveTrue();
    }

    @Override
    public List<Disability> searchDisability(String searchString) {
        return disabilityRepository.search(searchString);
    }

    @Override
    public Interest getInterestById(Long id) {
        log.info("CatalogServiceImpl.getInterestById id " + id);
        return interestRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE));
    }

    @Override
    public Interest createInterest(Interest interest) {
        interest.setId(null);
        return interestRepository.saveAndFlush(interest);
    }

    @Override
    public Interest updateInterest(Long id, Interest interest) {
        log.info(String.format("CatalogServiceImpl.updateInterest: %d. Interest {}: %s", id, interest));

        Interest dbInterest = interestRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        dbInterest.setName(interest.getName());

        return interestRepository.saveAndFlush(dbInterest);
    }

    @Override
    public void deleteInterest(Long id) {
        Interest dbInterest = interestRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        dbInterest.setIsActive(false);

        interestRepository.saveAndFlush(dbInterest);
    }

    @Override
    public List<Interest> getAllInterests() {
        return interestRepository.findAllByIsActiveTrue();
    }

    @Override
    public List<Interest> searchInterest(String searchString) {
        return interestRepository.search(searchString);
    }

    @Override
    public Organization getOrganizationById(Long id) {
        log.info("CatalogServiceImpl.getOrganizationById id " + id);
        return organizationRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE));
    }

    @Override
    public Organization createOrganization(Organization organization) {
        organization.setId(null);
        return organizationRepository.saveAndFlush(organization);
    }

    @Override
    public Organization updateOrganization(Long id, Organization organization) {
        log.info(String.format("CatalogServiceImpl.updateOrganization: %d. Organization {}: %s", id, organization));

        Organization dbOrganization = organizationRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        dbOrganization.setName(organization.getName());

        return organizationRepository.saveAndFlush(dbOrganization);
    }

    @Override
    public void deleteOrganization(Long id) {
        Organization dbOrganization = organizationRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        dbOrganization.setIsActive(false);

        organizationRepository.saveAndFlush(dbOrganization);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAllByIsActiveTrue();
    }

    @Override
    public List<Organization> searchOrganization(String searchString) {
        return organizationRepository.search(searchString);
    }

    @Override
    public Pathology getPathologyById(Long id) {
        log.info("CatalogServiceImpl.getPathologyById id " + id);
        return pathologyRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE));
    }

    @Override
    public Pathology createPathology(Pathology pathology) {
        pathology.setId(null);
        return pathologyRepository.saveAndFlush(pathology);
    }

    @Override
    public Pathology updatePathology(Long id, Pathology pathology) {
        log.info(String.format("CatalogServiceImpl.updatePathology: %d. Pathology {}: %s", id, pathology));

        Pathology dbPathology = pathologyRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        dbPathology.setName(pathology.getName());

        return pathologyRepository.saveAndFlush(dbPathology);
    }

    @Override
    public void deletePathology(Long id) {
        Pathology dbPathology = pathologyRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        dbPathology.setIsActive(false);

        pathologyRepository.saveAndFlush(dbPathology);
    }

    @Override
    public List<Pathology> getAllPathologies() {
        return pathologyRepository.findAllByIsActiveTrue();
    }

    @Override
    public List<Pathology> searchPathology(String searchString) {
        return pathologyRepository.search(searchString);
    }

    @Override
    public Tag getTagById(Long id) {
        log.info("CatalogServiceImpl.getTagById id " + id);
        return tagRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE));
    }

    @Override
    public Tag createTag(Tag tag) {
        tag.setId(null);
        return tagRepository.saveAndFlush(tag);
    }

    @Override
    public Tag updateTag(Long id, Tag tag) {
        log.info(String.format("CatalogServiceImpl.updateTag: %d. Tag {}: %s", id, tag));

        Tag dbTag = tagRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        dbTag.setName(tag.getName());

        return tagRepository.saveAndFlush(dbTag);
    }

    @Override
    public void deleteTag(Long id) {
        Tag dbTag = tagRepository.findById(id).orElseThrow(
                () -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE,
                        ErrorMessageConstants.DataNotFound.ERROR_CODE)
        );

        dbTag.setIsActive(false);

        tagRepository.saveAndFlush(dbTag);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAllByIsActiveTrue();
    }

    @Override
    public List<Tag> searchTag(String searchString) {
        return tagRepository.search(searchString);
    }
}
