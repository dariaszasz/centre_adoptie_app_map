package service;

import models.Animal;
import models.Volunteer;
import repository.IRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class that handles the business logic related to volunteers and animals.
 * Provides methods for adding, updating, deleting volunteers, assigning animals to volunteers,
 * sorting and filtering volunteers, and generating unique IDs for volunteers.
 */
public class VolunteerService {
    private final IRepository<Animal> animalRepository;
    private final IRepository<Volunteer> volunteerRepository;

    /**
     * Constructor to initialize the repository for volunteers and animals.
     *
     * @param volunteerRepository The repository to manage volunteer data.
     * @param animalRepository The repository to manage animal data.
     */
    public VolunteerService(IRepository<Volunteer> volunteerRepository, IRepository<Animal> animalRepository) {
        this.volunteerRepository = volunteerRepository;
        this.animalRepository = animalRepository;
    }

    /**
     * Adds a new volunteer to the system. Assigns a unique ID before adding the volunteer.
     *
     * @param volunteer The volunteer object to be added.
     */
    public void addVolunteer(Volunteer volunteer) {
        volunteer.setId(generateUniqueId());
        volunteerRepository.add(volunteer);
    }

    /**
     * Retrieves a list of all volunteers in the system.
     *
     * @return A list of all volunteers.
     */
    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.getAll();
    }

    /**
     * Retrieves a volunteer by their ID.
     *
     * @param id The ID of the volunteer.
     * @return The volunteer object if found, otherwise null.
     */
    public Volunteer getVolunteerById(int id) {
        return volunteerRepository.getById(id);
    }

    /**
     * Updates the information of a volunteer in the system.
     *
     * @param volunteer The volunteer object with updated information.
     */
    public void updateVolunteer(Volunteer volunteer) {
        volunteerRepository.update(volunteer);
    }

    /**
     * Deletes a volunteer by their ID.
     *
     * @param id The ID of the volunteer to be deleted.
     */
    public void deleteVolunteer(int id) {
        volunteerRepository.delete(id);
    }

    /**
     * Sorts the list of volunteers based on their experience in descending order.
     *
     * @return A list of volunteers sorted by experience.
     */
    public List<Volunteer> sortVolunteersByExperience() {
        return volunteerRepository.getAll().stream()
                .sorted((v1, v2) -> v2.getExperience().compareTo(v1.getExperience()))
                .collect(Collectors.toList());
    }

    /**
     * Filters the list of volunteers based on the minimum number of shelters they are involved in.
     *
     * @param minShelters The minimum number of shelters a volunteer must be involved in.
     * @return A list of volunteers who are involved in at least the specified number of shelters.
     */
    public List<Volunteer> filterVolunteersBySheltersCount(int minShelters) {
        return volunteerRepository.getAll().stream()
                .filter(volunteer -> volunteer.getShelters().size() >= minShelters)
                .collect(Collectors.toList());
    }

    /**
     * Assigns an animal to a volunteer. If the volunteer or animal is not found, an error message is returned.
     *
     * @param volunteerId The ID of the volunteer.
     * @param animalId The ID of the animal.
     * @return A message indicating the result of the assignment (success or error).
     */
    public String assignAnimalToVolunteer(int volunteerId, int animalId) {
        Optional<Volunteer> volunteerOpt = volunteerRepository.getAll().stream()
                .filter(v -> v.getId() == volunteerId)
                .findFirst();

        if (volunteerOpt.isEmpty()) {
            return "Voluntar cu ID-ul " + volunteerId + " nu a fost găsit.";
        }

        Volunteer volunteer = volunteerOpt.get();

        Optional<Animal> animalOpt = animalRepository.getAll().stream()
                .filter(a -> a.getId() == animalId)
                .findFirst();

        if (animalOpt.isEmpty()) {
            return "Animal cu ID-ul " + animalId + " nu a fost găsit.";
        }

        Animal animal = animalOpt.get();

        volunteer.addAnimal(animal);
        animal.setAssignedVolunteer(volunteer);

        return "Animalul " + animal.getName() + " a fost atribuit voluntarului " + volunteer.getName();
    }

    /**
     * Generates a unique ID for a new volunteer by finding the maximum ID in the existing list of volunteers
     * and adding 1 to it.
     *
     * @return The generated unique ID for the volunteer.
     */
    public int generateUniqueId() {
        List<Volunteer> volunteers = volunteerRepository.getAll();
        if (volunteers.isEmpty()) {
            return 1;  // If there are no volunteers, return 1 as the ID.
        }

        return volunteers.stream()
                .mapToInt(Volunteer::getId)
                .max()
                .orElse(0) + 1;
    }
}
