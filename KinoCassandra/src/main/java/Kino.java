package pl.kielce.tu.cassandra.mapper;
import java.util.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;

public class Kino {


	static Scanner scanner;
	static boolean koniec = false;
	static FilmDao dao;
	static CqlSession session;


	static private void getWyborMenu() {
		int choice = -1;
		do {
			System.out.println("Podaj wybór:");
			choice = Integer.parseInt(scanner.nextLine());
			if (choice < 0 || choice > 6) {
				System.out.println("Brak takiej opcji!");
			}
		} while (choice < 0 || choice > 6);
		akcja(choice);
	}
	static private void akcja(int wybor) {
		switch (wybor) {
			case 1:
				addNew();
				break;
			case 2:
				findById();
				break;
			case 3:
				//update();
				break;
			case 4:
				delete();
				break;
			case 0:
				koniec = true;
				break;
			default:
				System.out.println("BŁĄD");
		}
	}
	static void menu() {
		while (!koniec) {
			showMenu();
			getWyborMenu();
		}
	}
	static private  void delete(){
		System.out.println("Podaj id:");
		int id = Integer.parseInt(scanner.nextLine());
		Film film = dao.findById(id);
		dao.delete(film);

	}
	private static void findById() {
		System.out.println("Podaj id:");
		int id = Integer.parseInt(scanner.nextLine());
		Film film = dao.findById(id);
		System.out.println("Wizyta{" +
				"id=" + film.id +
				", tytul='" + film.tytul + '\'' +
				", imieRezysera='" + film.imieRezysera + '\'' +
				", nazwiskoRezysera='" + film.nazwiskoRezysera + '\'' +
				", czas='" + film.czas + '\'' +
				", Seans{" +
				"  sala='" + film.seans.sala + '\'' +
				", ilMiejsc='" + film.seans.ilMiejsc + '\'' +
				'}');
	}
	private static void addNew() {
		Film film = new Film();
		Seans seans = new Seans();
		System.out.println("Podaj id:");
		film.id = Integer.parseInt(scanner.nextLine());
		System.out.println("Podaj tytul:");
		film.tytul = scanner.nextLine();
		System.out.println("Podaj imie rezysera:");
		film.imieRezysera = scanner.nextLine();
		System.out.println("Podaj nazwisko rezysera:");
		film.nazwiskoRezysera = scanner.nextLine();
		System.out.println("Podaj czas:");
		film.czas = scanner.nextLine();
		System.out.println("Podaj numer sali:");
		seans.sala = Integer.parseInt(scanner.nextLine());
		System.out.println("Podaj ilosc miejsc:");
		seans.ilMiejsc = Integer.parseInt(scanner.nextLine());
		film.seans = seans;
		dao.save(film);
	}
	static private void showMenu() {
		System.out.println("\nKino");
		System.out.println("1 - Dodaj film ");
		System.out.println("2 - Wyświetl wszystkie fimy ");
		System.out.println("3 - Wyświetl film po ID ");
		System.out.println("4 - Zaaktualizuj film");
		System.out.println("5 - Usuń film");
		System.out.println("0 - WYJŚCIE\n");
	}

	public static void main(String[] args) {

		Logger logger = Logger.getLogger("");
		logger.setLevel(Level.OFF);

		Builder builder = new Builder();
		session = builder.init();

		CodecManager codecManager = new CodecManager(session);
		codecManager.registerSeansCodec();

		KinoTableManager kinoTableManager = new KinoTableManager(session);
		kinoTableManager.createTable();

		FilmMapper filmMapper = new FilmMapperBuilder(session).build();
		dao = filmMapper.filmDao(CqlIdentifier.fromCql("film"));

		menu();
		scanner.nextLine();

		}}
