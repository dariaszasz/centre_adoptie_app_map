import controller.AdoptantController;
import exceptions.BusinessLogicException;
import models.Adoptant;
import models.Animal;
import models.AnimalType;
import models.Volunteer;
import controller.VolunteerController;
import presentation.AdoptantManagement;
import repository.IRepository;
import repository.InMemoryRepository;
import service.AnimalService;
import models.AdoptionRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.VolunteerService;
import service.AdoptantService;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for validating CRUD operations and business logic of the application.
 */
public class ApplicationTest {

    private AnimalService animalService;
    private VolunteerController volunteerController;
    private AdoptantController adoptantController;
    private AdoptantManagement adoptantManagement;
    private AdoptantService adoptantService;



    @BeforeEach
    void setUp() {
        // Initialize in-memory repository and service pentru Animal
        InMemoryRepository<Animal> animalRepository = new InMemoryRepository<>();
        animalService = new AnimalService(animalRepository);

        // Repository pentru Volunteer fără a impune IStatusEntity
        InMemoryRepository<Volunteer> volunteerRepository = new InMemoryRepository<>();
        InMemoryRepository<Adoptant> adoptantRepository = new InMemoryRepository<>();
        InMemoryRepository<AdoptionRequest> adoptionRequestRepository = new InMemoryRepository<>();

        // Creăm serviciul VolunteerService
        VolunteerService volunteerService = new VolunteerService(volunteerRepository, animalRepository);
        volunteerController = new VolunteerController(volunteerService);

        // Instanțiem serviciul AdoptantService
        adoptantService = new AdoptantService(adoptantRepository, adoptionRequestRepository);

        // Instanțiem controller-ul cu serviciul creat
        adoptantController = new AdoptantController(adoptantService, animalService);
        adoptantManagement = new AdoptantManagement(adoptantController);
    }




    @Test
    void testCRUDOperations() {
        // Arrange: Create an AnimalType and an Animal
        AnimalType dogType = new AnimalType(1, "Dog", "Loyal and friendly");
        Animal animal = new Animal(1, "Bella", dogType, 3, "Healthy");

        // Act: Perform CRUD operations
        animalService.addAnimal(animal); // Create
        Optional<Animal> retrievedAnimal = animalService.getAnimalById(1); // Read

        // Assert: Validate Create and Read
        assertTrue(retrievedAnimal.isPresent()); // Verifică dacă animalul a fost găsit
        Animal foundAnimal = retrievedAnimal.get(); // Obține animalul din Optional
        assertEquals("Bella", foundAnimal.getName());
        assertEquals("Dog", foundAnimal.getAnimalType().getTypeName());

        // Update: Change the status and update the animal
        animal.setStatus("Adopted");
        animalService.updateAnimal(animal);
        Optional<Animal> updatedAnimal = animalService.getAnimalById(1);

        // Assert: Validate Update
        assertTrue(updatedAnimal.isPresent());
        Animal updatedFoundAnimal = updatedAnimal.get(); // Obține animalul actualizat
        assertEquals("Adopted", updatedFoundAnimal.getStatus());

        // Delete: Remove the animal
        animalService.deleteAnimal(1);
        Optional<Animal> deletedAnimal = animalService.getAnimalById(1);

        // Assert: Validate Delete
        assertFalse(deletedAnimal.isPresent()); // Verifică dacă animalul nu mai există
    }





    @Test
    void testFilterAnimalsByStatus() {
        // Arrange: Add multiple animals with different statuses
        AnimalType dogType = new AnimalType(1, "Dog", "Loyal and friendly");
        AnimalType catType = new AnimalType(2, "Cat", "Independent and curious");

        animalService.addAnimal(new Animal(1, "Bella", dogType, 3, "Available"));
        animalService.addAnimal(new Animal(2, "Luna", catType, 2, "Adopted"));
        animalService.addAnimal(new Animal(3, "Max", dogType, 4, "Available"));

        // Act: Filter animals by status
        List<Animal> availableAnimals = animalService.filterAnimalsByStatus("Available");

        // Assert: Validate filtering
        assertEquals(2, availableAnimals.size());
        assertTrue(availableAnimals.stream().allMatch(a -> a.getStatus().equals("Available")));
    }

    @Test
    void testSortAnimalsByAge() {
        // Arrange: Add multiple animals with different ages
        AnimalType dogType = new AnimalType(1, "Dog", "Loyal and friendly");
        AnimalType catType = new AnimalType(2, "Cat", "Independent and curious");

        animalService.addAnimal(new Animal(1, "Bella", dogType, 3, "Healthy"));
        animalService.addAnimal(new Animal(2, "Luna", catType, 2, "Healthy"));
        animalService.addAnimal(new Animal(3, "Max", dogType, 4, "Injured"));

        // Act: Sort animals by age
        List<Animal> sortedAnimals = animalService.sortAnimalsByAge();

        // Assert: Validate sorting
        assertEquals("Luna", sortedAnimals.get(0).getName()); // Luna is the youngest
        assertEquals("Bella", sortedAnimals.get(1).getName()); // Bella is the middle
        assertEquals("Max", sortedAnimals.get(2).getName()); // Max is the oldest
    }

    @Test
    void testExceptionHandling() {
        assertThrows(BusinessLogicException.class, () -> {
            // Tentați să adăugați un animal null, care ar trebui să arunce excepția.
            animalService.addAnimal(null);
        });
    }


    @Test
    void testUpdateAnimalNotFound() {
        // Arrange: Creăm un animal care nu există în repository
        AnimalType dogType = new AnimalType(1, "Dog", "Loyal and friendly");
        Animal animal = new Animal(1, "Bella", dogType, 3, "Healthy");

        // Act: Încercăm să actualizăm un animal care nu există (ID-ul 1 nu există încă)
        animal.setStatus("Adopted");

        // Assert: Verificăm dacă se aruncă excepția
        assertThrows(BusinessLogicException.class, () -> {
            animalService.updateAnimal(animal);
        });
    }



    @Test
    void testDeleteAnimalNotFound() {
        // Act: Încercăm să ștergem un animal care nu există (ID-ul 999 nu există)
        // Assert: Verificăm dacă se aruncă excepția
        assertThrows(BusinessLogicException.class, () -> {
            animalService.deleteAnimal(999); // ID-ul 999 nu există
        });
    }

    @Test
    void testFilterAnimalsByStatusNotFound() {
        // Arrange: Adăugăm câteva animale
        AnimalType dogType = new AnimalType(1, "Dog", "Loyal and friendly");
        animalService.addAnimal(new Animal(1, "Bella", dogType, 3, "Healthy"));
        animalService.addAnimal(new Animal(2, "Luna", dogType, 2, "Adopted"));

        // Act & Assert: Verificăm că se aruncă excepția când nu există animale cu statutul "Lost"
        assertThrows(BusinessLogicException.class, () -> {
            animalService.filterAnimalsByStatus("Lost");
        });
    }


    @Test
    void testSortAnimalsByAgeEmptyList() {
        // Act: Încercăm să sortăm o listă goală
        assertThrows(BusinessLogicException.class, () -> {
            animalService.sortAnimalsByAge();
        });
    }

    @Test
    void testCRUDOperationsForVolunteers() {
        // Arrange: Crează un voluntar
        Volunteer volunteer = new Volunteer(1, "John Doe", "john@example.com", "2 years");

        // Act: Adaugă voluntarul
        volunteerController.addVolunteer(volunteer);

        // Assert: Verifică dacă voluntarul a fost adăugat corect
        Volunteer retrievedVolunteer = volunteerController.getVolunteerById(1);
        assertNotNull(retrievedVolunteer);
        assertEquals("John Doe", retrievedVolunteer.getName());

        // Update: Modifică detaliile voluntarului
        volunteer.setName("Jane Doe");
        volunteerController.updateVolunteer(volunteer);

        // Assert: Verifică dacă detaliile au fost actualizate
        Volunteer updatedVolunteer = volunteerController.getVolunteerById(1);
        assertEquals("Jane Doe", updatedVolunteer.getName());

        // Delete: Șterge voluntarul
        volunteerController.deleteVolunteer(1);
        Volunteer deletedVolunteer = volunteerController.getVolunteerById(1);

        // Assert: Verifică dacă voluntarul a fost șters
        assertNull(deletedVolunteer);
    }

    @Test
    void testAssignAnimalToVolunteer() {
        // Arrange: Crează un voluntar și un animal
        Volunteer volunteer = new Volunteer(1, "John Doe", "john@example.com", "2 years");
        Animal animal = new Animal(1, "Bella", new AnimalType(1, "Dog", "Loyal and friendly"), 3, "Healthy");

        // Adaugă voluntarul și animalul
        volunteerController.addVolunteer(volunteer);
        animalService.addAnimal(animal);

        // Act: Asignează animalul voluntarului
        String response = volunteerController.assignAnimalToVolunteer(1, 1);

        // Assert: Verifică dacă animalul a fost asignat corect
        assertEquals("Animal Bella has been assigned to volunteer John Doe", response);

        // Verifică dacă animalul este asociat corect cu voluntarul
        assertTrue(volunteerController.getVolunteerById(1).getAssignedAnimals().contains(animal));
    }


    @Test
    void testGetAllVolunteers() {
        // Arrange: Crează mai mulți voluntari
        Volunteer volunteer1 = new Volunteer(1, "John Doe", "john@example.com", "2 years");
        Volunteer volunteer2 = new Volunteer(2, "Jane Smith", "jane@example.com", "3 years");

        volunteerController.addVolunteer(volunteer1);
        volunteerController.addVolunteer(volunteer2);

        // Act: Obține toți voluntarii
        List<Volunteer> volunteers = volunteerController.getAllVolunteers();

        // Assert: Verifică dacă lista de voluntari conține cei adăugați
        assertEquals(2, volunteers.size());
        assertTrue(volunteers.stream().anyMatch(v -> v.getName().equals("John Doe")));
        assertTrue(volunteers.stream().anyMatch(v -> v.getName().equals("Jane Smith")));
    }

    @Test
    public void testAddAdoptant() {
        // Creare obiect adoptant
        Adoptant adoptant = new Adoptant(0, "Jane Doe", "jane.doe@example.com");

        // Adăugare adoptant
        adoptantService.addAdoptant(adoptant);

        // Verificare dacă adoptantul a fost adăugat
        Adoptant addedAdoptant = adoptantService.getAdoptantById(adoptant.getId());
        assertNotNull(addedAdoptant, "Adoptantul ar trebui să fie adăugat.");
        assertEquals("Jane Doe", addedAdoptant.getName(), "Numele adoptantului ar trebui să fie corect.");
        assertEquals("jane.doe@example.com", addedAdoptant.getContactDetails(), "Detaliile de contact ar trebui să fie corecte.");
    }


    @Test
    void testViewAllAdoptants() {
        // Arrange: Adăugăm câțiva adoptanți în sistem
        adoptantController.addAdoptant("John Doe", "john.doe@example.com");
        adoptantController.addAdoptant("Jane Smith", "jane.smith@example.com");

        // Act: Obținem lista tuturor adoptanților
        List<Adoptant> adoptants = adoptantController.getAllAdoptants();

        // Assert: Verificăm dacă lista este corectă
        assertNotNull(adoptants);
        assertEquals(2, adoptants.size());
        assertEquals("John Doe", adoptants.get(0).getName());
        assertEquals("Jane Smith", adoptants.get(1).getName());

        // Verificare afișare (output în consolă)
        System.out.println("Adoptants:");
        adoptants.forEach(System.out::println);
    }

}
