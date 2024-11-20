package service;

import models.Volunteer;
import repository.IRepository;

import java.util.List;
import java.util.stream.Collectors;

public class VolunteerService {
    private IRepository<Volunteer> volunteerRepository;

    public VolunteerService(IRepository<Volunteer> volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    // Method to add a volunteer
    public void addVolunteer(Volunteer volunteer) {
        volunteerRepository.add(volunteer);
    }

    // Method to get all volunteers
    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.getAll();
    }

    // Method to get a volunteer by ID
    public Volunteer getVolunteerById(int id) {
        return volunteerRepository.getById(id);
    }

    // Method to update a volunteer
    public void updateVolunteer(Volunteer volunteer) {
        volunteerRepository.update(volunteer);
    }

    // Method to delete a volunteer
    public void deleteVolunteer(int id) {
        volunteerRepository.delete(id);
    }

    // Sorting volunteers by their experience level
    public List<Volunteer> sortVolunteersByExperience() {
        return volunteerRepository.getAll().stream()
                .sorted((v1, v2) -> v2.getExperience().compareTo(v1.getExperience()))  // Assuming experience is a String
                .collect(Collectors.toList());
    }

    // Filtering volunteers by the number of shelters they are involved in
    public List<Volunteer> filterVolunteersBySheltersCount(int minShelters) {
        return volunteerRepository.getAll().stream()
                .filter(volunteer -> volunteer.getShelters().size() >= minShelters)
                .collect(Collectors.toList());
    }
}


