package service;

import models.Animal;
import models.Volunteer;
import repository.IRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VolunteerService {
    private final IRepository<Animal> animalRepository;
    private final IRepository<Volunteer> volunteerRepository;

    // Constructor pentru a inițializa repository-urile pentru voluntari și animale
    public VolunteerService(IRepository<Volunteer> volunteerRepository, IRepository<Animal> animalRepository) {
        this.volunteerRepository = volunteerRepository;
        this.animalRepository = animalRepository;
    }

    // Adaugă un voluntar în repository
    public void addVolunteer(Volunteer volunteer) {
        volunteer.setId(generateUniqueId());  // Setează un ID unic înainte de adăugare
        volunteerRepository.add(volunteer);
    }

    // Returnează lista completă a voluntarilor
    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.getAll();
    }

    // Returnează un voluntar pe baza ID-ului
    public Volunteer getVolunteerById(int id) {
        return volunteerRepository.getById(id);
    }

    // Actualizează informațiile unui voluntar
    public void updateVolunteer(Volunteer volunteer) {
        volunteerRepository.update(volunteer);
    }

    // Șterge un voluntar din repository pe baza ID-ului
    public void deleteVolunteer(int id) {
        volunteerRepository.delete(id);
    }

    // Sortează voluntarii în funcție de nivelul lor de experiență (presupunem că experiența este un String)
    public List<Volunteer> sortVolunteersByExperience() {
        return volunteerRepository.getAll().stream()
                .sorted((v1, v2) -> v2.getExperience().compareTo(v1.getExperience()))  // Compară experiența voluntarilor
                .collect(Collectors.toList());
    }

    // Filtrează voluntarii pe baza numărului de adăposturi în care sunt implicați
    public List<Volunteer> filterVolunteersBySheltersCount(int minShelters) {
        return volunteerRepository.getAll().stream()
                .filter(volunteer -> volunteer.getShelters().size() >= minShelters)
                .collect(Collectors.toList());
    }

    // Metoda pentru a atribui un animal unui voluntar
    public String assignAnimalToVolunteer(int volunteerId, int animalId) {
        // Găsește voluntarul după ID
        Optional<Volunteer> volunteerOpt = volunteerRepository.getAll().stream()
                .filter(v -> v.getId() == volunteerId)
                .findFirst();

        if (volunteerOpt.isEmpty()) {
            // Dacă nu s-a găsit voluntarul, returnează mesaj de eroare
            return "Voluntar cu ID-ul " + volunteerId + " nu a fost găsit.";
        }

        Volunteer volunteer = volunteerOpt.get();

        // Găsește animalul după ID
        Optional<Animal> animalOpt = animalRepository.getAll().stream()
                .filter(a -> a.getId() == animalId)
                .findFirst();

        if (animalOpt.isEmpty()) {
            // Dacă nu s-a găsit animalul, returnează mesaj de eroare
            return "Animal cu ID-ul " + animalId + " nu a fost găsit.";
        }

        Animal animal = animalOpt.get();

        // Atribuie animalul voluntarului și viceversa
        volunteer.addAnimal(animal);
        animal.setAssignedVolunteer(volunteer);

        // Returnează un mesaj de succes
        return "Animalul " + animal.getName() + " a fost atribuit voluntarului " + volunteer.getName();
    }

    // Metoda pentru a genera un ID unic pentru voluntari
    public int generateUniqueId() {
        // Găsește cel mai mare ID existent în lista de voluntari și returnează ID-ul + 1
        List<Volunteer> volunteers = volunteerRepository.getAll();
        if (volunteers.isEmpty()) {
            return 1; // Dacă nu există niciun voluntar, returnează 1 ca ID
        }

        // Găsește ID-ul maxim și adaugă 1 pentru a crea un ID unic
        return volunteers.stream()
                .mapToInt(Volunteer::getId)  // Extrage ID-urile voluntarilor
                .max()  // Găsește cel mai mare ID
                .orElse(0) + 1;  // Dacă nu găsește niciun ID, va folosi 0 și va adăuga 1
    }
}
