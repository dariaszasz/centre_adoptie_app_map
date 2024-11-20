package service;

import models.Volunteer;
import repository.IRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class responsible for managing volunteer-related operations.
 * It interacts with the repository to add, update, delete, and retrieve volunteers.
 */
public class VolunteerService {
    private IRepository<Volunteer> volunteerRepository;

    /**
     * Constructor for the VolunteerService.
     *
     * @param volunteerRepository The repository for volunteers.
     */
    public VolunteerService(IRepository<Volunteer> volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    /**
     * Adds a new volunteer to the repository.
     *
     * @param volunteer The volunteer to be added.
     */
    public void addVolunteer(Volunteer volunteer) {
        volunteerRepository.add(volunteer);
    }

    /**
     * Retrieves all volunteers from the repository.
     *
     * @return A list of all volunteers.
     */
    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.getAll();
    }

    /**
     * Retrieves a volunteer by their ID.
     *
     * @param id The ID of the volunteer to retrieve.
     * @return The volunteer with the given ID, or null if not found.
     */
    public Volunteer getVolunteerById(int id) {
        return volunteerRepository.getById(id);
    }

    /**
     * Updates the information of an existing volunteer in the repository.
     *
     * @param volunteer The volunteer with updated information.
     */
    public void updateVolunteer(Volunteer volunteer) {
        volunteerRepository.update(volunteer);
    }

    /**
     * Deletes a volunteer from the repository by their ID.
     *
     * @param id The ID of the volunteer to be deleted.
     */
    public void deleteVolunteer(int id) {
        volunteerRepository.delete(id);
    }

    /**
     * Sorts volunteers by their experience level in descending order.
     *
     * @return A list of volunteers sorted by experience level.
     */
    public List<Volunteer> sortVolunteersByExperience() {
        return volunteerRepository.getAll().stream()
                .sorted((v1, v2) -> v2.getExperience().compareTo(v1.getExperience()))  // Assuming experience is a String
                .collect(Collectors.toList());
    }

    /**
     * Filters volunteers based on the number of shelters they are involved in.
     *
     * @param minShelters The minimum number of shelters a volunteer must be involved in.
     * @return A list of volunteers who are involved in at least the given number of shelters.
     */
    public List<Volunteer> filterVolunteersBySheltersCount(int minShelters) {
        return volunteerRepository.getAll().stream()
                .filter(volunteer -> volunteer.getShelters().size() >= minShelters)
                .collect(Collectors.toList());
    }
}
