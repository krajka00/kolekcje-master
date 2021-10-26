package tb.soft;

import java.util.*;

/**
 * Program: Aplikacja działająca w oknie konsoli, która umożliwia testowanie 
 *          operacji wykonywanych na obiektach klasy Person.
 *    Plik: PersonConsoleApp.java
 *          
 *   Autor: Paweł Rogaliński
 *    Data: październik 2018 r.
 */
public class PersonConsoleApp {

	private static final String GREETING_MESSAGE = 
			"Program Person - wersja konsolowa\n" + 
	        "Autor: Paweł Rogaliński\n" +
			"Data:  październik 2018 r.\n";

	private static final String MENU = 
			"    M E N U   G Ł Ó W N E  \n" +
			"1 - Podaj dane nowej osoby \n" +
			"2 - Informację o stanowisku konkretnej osoby      \n" +
			"3 - Informację o aktualnie przechowywanych stanowiskach   \n" +
			"4 - Wszystkie lata urodzeń dodanych przez użytkownika    \n" +
			"5 - Usuwanie danych   \n" +
			"0 - Zakończ program        \n";	
	
	private static final String CHANGE_MENU = 
			"   Co zmienić?     \n" + 
	        "1 - Imię           \n" + 
			"2 - Nazwisko       \n" + 
	        "3 - Rok urodzenia  \n" + 
			"4 - Stanowisko     \n" +
	        "0 - Powrót do menu głównego\n";

	
	/**
	 * ConsoleUserDialog to pomocnicza klasa zawierająca zestaw
	 * prostych metod do realizacji dialogu z użytkownikiem
	 * w oknie konsoli tekstowej.
	 */
	private static final ConsoleUserDialog UI = new ConsoleUserDialog();
	
	
	public static void main(String[] args) {
		// Utworzenie obiektu aplikacji konsolowej
		// oraz uruchomienie głównej pętli aplikacji.
		PersonConsoleApp application = new PersonConsoleApp();
		application.runMainLoop();
		System.exit(0);
	} 

	
	/*
	 *  Referencja do obiektu, który zawiera dane aktualnej osoby.
	 */
	private Person currentPerson = null;



	/*
	 *  Metoda runMainLoop wykonuje główną pętlę aplikacji.
	 *  UWAGA: Ta metoda zawiera nieskończoną pętlę,
	 *         w której program się zatrzymuje aż do zakończenia
	 *         działania za pomocą metody System.exit(0); 
	 */
	public void runMainLoop() {

		UI.printMessage(GREETING_MESSAGE);
		List<Integer> PeopleArray = new ArrayList<>();
		HashSet<Integer> PeopleHash = new HashSet<>();
		TreeSet<Integer> PeopleTree = new TreeSet<>();
		LinkedList<Integer> PeopleLinked = new LinkedList<>();
		HashSet<String> Name = new HashSet<>();
		HashMap<String, PersonJob> PersonAndJobHash = new HashMap<>();
		TreeMap<String, PersonJob> PersonAndJobTree = new TreeMap<>();
		Integer year;
		String Job;
		String LastName;

		while (true) {
			UI.clearConsole();
			showCurrentPerson();

			try {
				switch (UI.enterInt(MENU + "==>> ")) {
				case 1:
					// utworzenie nowej osoby
					currentPerson = createNewPerson();
					PeopleArray.add(currentPerson.getBirthYear());
					PeopleHash.add(currentPerson.getBirthYear());
					PeopleTree.add(currentPerson.getBirthYear());
					PeopleLinked.add(currentPerson.getBirthYear());
					if(Name.contains(currentPerson.getLastName())){
						System.out.println("Nie można dodać tej osoby do bazy danych stanowisk, ponieważ Jest już ktoś z tym nazwiskiem" );
					}
					else {
						Name.add(currentPerson.getLastName());
						PersonAndJobHash.put(currentPerson.getLastName(), currentPerson.getJob());
						PersonAndJobTree.put(currentPerson.getLastName(), currentPerson.getJob());
					}



					break;
				case 2:
					// usunięcie danych aktualnej osoby.
					//currentPerson = null;
					//UI.printInfoMessage("Dane aktualnej osoby zostały usunięte");
					//break;
					System.out.println("Nazwiska otychczasowo dodanych osób przechowywanyc za pomocą Hashmapy:");
					PersonAndJobHash.keySet().forEach(System.out::println);
					Job=UI.enterString("Podaj nazwisko osoby, której stanowisko cię interesuje:");
					System.out.println("Stanowisko podanej osoby to: "+PersonAndJobHash.get(Job));
					System.out.println("Nazwiska otychczasowo dodanych osób przechowywanyc za pomocą Treemapy:");
					PersonAndJobTree.keySet().forEach(System.out::println);
					Job=UI.enterString("Podaj nazwisko osoby, której stanowisko cię interesuje:");
					System.out.println("Stanowisko podanej osoby to: "+PersonAndJobTree.get(Job));


				case 3:
					// zmiana danych dla aktualnej osoby
					if (currentPerson == null) throw new PersonException("Żadna osoba nie została utworzona.");
					System.out.println("Aktualnie stanowiska przechowywane przez Hashmapę:");
					PersonAndJobHash.values().forEach(System.out::println);
					System.out.println("Aktualnie stanowiska przechowywane przez Treemapę:");
					PersonAndJobTree.values().forEach(System.out::println);
					break;

				case 4: {
					System.out.println("Wszystkie dodane lata urodzenia dodane za pomocą ArrayList");
					PeopleArray.forEach(System.out::println);
					System.out.println("Wszystkie dodane lata urodzenia dodane za pomocą Hashsetu");
					PeopleHash.forEach(System.out::println);
					System.out.println("Wszystkie dodane lata urodzenia dodane za pomocą Treesetu");
					PeopleTree.forEach(System.out::println);
					System.out.println("Wszystkie dodane lata urodzenia dodane za pomocą Linkedlisty");
					PeopleLinked.forEach(System.out::println);
				}
					break;
				case 5: {
					if(PeopleArray.isEmpty()){
						System.out.println("Lista przechowywanych lat jest pusta. Musisz najpierw dodać rok aby móc usunąć");

					}
					else {
						System.out.println("Aktualnie przechowywane lata za pomocą Arraylisty:");
						PeopleArray.forEach(System.out::println);
						year = UI.enterInt("Podaj datę urodzenia którą chcesz usunąć:");
						PeopleArray.remove(year);
						System.out.println("Usunięto rok :" + year);
						System.out.println("Aktualnie przechowywane lata za pomocą Hashsetu:");
						PeopleHash.forEach(System.out::println);
						year = UI.enterInt("Podaj datę urodzenia którą chcesz usunąć:");
						PeopleHash.remove(year);
						System.out.println("Usunięto rok :" + year);
						System.out.println("Aktualnie przechowywane lata za pomocą Treesetu:");
						PeopleTree.forEach(System.out::println);
						year = UI.enterInt("Podaj datę urodzenia którą chcesz usunąć:");
						PeopleTree.remove(year);
						System.out.println("Usunięto rok :" + year);
						System.out.println("Nazwiska otychczasowo dodanych osób:");
						PersonAndJobHash.keySet().forEach(System.out::println);
						LastName=UI.enterString("Podaj nazwisko osoby, której stanowisko chcesz usunąć z bazy danych:");
						System.out.println("Stanowisko osoby o nazwisku: "+LastName +" zostało usunięte było to: "+PersonAndJobHash.get(LastName));
						PersonAndJobHash.remove(LastName);
						System.out.println("Nazwiska otychczasowo dodanych osób:");
						PersonAndJobTree.keySet().forEach(System.out::println);
						LastName=UI.enterString("Podaj nazwisko osoby, której stanowisko chcesz usunąć z bazy danych:");
						System.out.println("Stanowisko osoby o nazwisku: "+LastName +" zostało usunięte było to: "+PersonAndJobTree.get(LastName));
						PersonAndJobHash.remove(LastName);


			 		}
				}

					break;
				case 0:
					// zakończenie działania programu
					UI.printInfoMessage("\nProgram zakończył działanie!");
					System.exit(0);
				} // koniec instrukcji switch
			} catch (PersonException e) {
				// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
				// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
				// poszczególnych atrybutów.
				// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
				UI.printErrorMessage(e.getMessage());
			}
		} // koniec pętli while
	}
	
	
	/*
	 *  Metoda wyświetla w oknie konsoli dane aktualnej osoby 
	 *  pamiętanej w zmiennej currentPerson.
	 */
	void showCurrentPerson() {
		showPerson(currentPerson);
	} 

	
	/* 
	 * Metoda wyświetla w oknie konsoli dane osoby reprezentowanej 
	 * przez obiekt klasy Person
	 */ 
	static void showPerson(Person person) {
		StringBuilder sb = new StringBuilder();
		
		if (person != null) {
			sb.append("Aktualna osoba: \n")
			  .append("      Imię: ").append(person.getFirstName()).append("\n")
			  .append("  Nazwisko: ").append(person.getLastName()).append("\n")
			  .append("   Rok ur.: ").append(person.getBirthYear()).append("\n")
			  .append("Stanowisko: ").append(person.getJob()).append("\n");
		} else
			sb.append( "Brak danych osoby\n" );
		UI.printMessage( sb.toString() );
	}

	
	/* 
	 * Metoda wczytuje w konsoli dane nowej osoby, tworzy nowy obiekt
	 * klasy Person i wypełnia atrybuty wczytanymi danymi.
	 * Walidacja poprawności danych odbywa się w konstruktorze i setterach
	 * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
	 * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
	 */
	static Person createNewPerson(){

		String first_name = UI.enterString("Podaj imię: ");
		String last_name = UI.enterString("Podaj nazwisko: ");
		String birth_year = UI.enterString("Podaj rok ur.: ");
		UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
		String job_name = UI.enterString("Podaj stanowisko: ");
		Person person;
		try { 
			// Utworzenie nowego obiektu klasy Person oraz
			// ustawienie wartości wszystkich atrybutów.
			person = new Person(first_name, last_name);
			person.setBirthYear(birth_year);
			person.setJob(job_name);
		} catch (PersonException e) {    
			// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
			// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
			// poszczególnych atrybutów.
			// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
			UI.printErrorMessage(e.getMessage());
			return null;
		}
		return person;
	}
	
	
	/* 
	 * Metoda pozwala wczytać nowe dane dla poszczególnych atrybutów 
	 * obiekty person i zmienia je poprzez wywołanie odpowiednich setterów z klasy Person.
	 * Walidacja poprawności wczytanych danych odbywa się w setterach
	 * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
	 * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
	 */
	static void changePersonData(Person person)
	{
		while (true) {
			UI.clearConsole();
			showPerson(person);

			try {		
				switch (UI.enterInt(CHANGE_MENU + "==>> ")) {
				case 1:
					person.setFirstName(UI.enterString("Podaj imię: "));
					break;
				case 2:
					person.setLastName(UI.enterString("Podaj nazwisko: "));
					break;
				case 3:
					person.setBirthYear(UI.enterString("Podaj rok ur.: "));
					break;
				case 4:
					UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
					person.setJob(UI.enterString("Podaj stanowisko: "));
					break;
				case 0: return;
				}  // koniec instrukcji switch
			} catch (PersonException e) {     
				// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
				// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
				// poszczególnych atrybutów.
				// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
				UI.printErrorMessage(e.getMessage());
			}
		}
	}

	
}  // koniec klasy PersonConsoleApp
