package de.dis2023.core;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import de.dis2023.data.House;
import de.dis2023.data.Estate;
import de.dis2023.data.PurchaseContract;
import de.dis2023.data.EstateAgent;
import de.dis2023.data.TenancyContract;
import de.dis2023.data.Person;
import de.dis2023.data.Apartment;
import de.dis2023.data.Contract;

/**
 *  Class for managing all database entities.
 * 
 * TODO: All data is currently stored in memory. 
 * The aim of the exercise is to gradually outsource data management to the 
 * database. When the work is done, all sets in this class become obsolete!
 */
public class EstateService {
	//TODO All these sets should be commented out after successful implementation.
	/**

	private Set<EstateAgent> estateAgents = new HashSet<EstateAgent>();
	private Set<Person> persons = new HashSet<Person>();
	private Set<House> houses = new HashSet<House>();
	private Set<Apartment> apartments = new HashSet<Apartment>();
	private Set<TenancyContract> tenancyContracts = new HashSet<TenancyContract>();
	private Set<PurchaseContract> purchaseContracts = new HashSet<PurchaseContract>();

	private Set<Contract> contracts = new HashSet<Contract>();
	 */
	
	//Hibernate Session
	private SessionFactory sessionFactory;
	
	public EstateService() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	/**
	 * Find an estate agent with the given id
	 * @param id The ID of the agent
	 * @return Agent with ID or null
	 */
	public EstateAgent getEstateAgentByID(int id) {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		EstateAgent agent = session.get(EstateAgent.class, id);
		session.getTransaction().commit();
		session.close();
		
		return agent;
	}
	
	/**
	 * Find estate agent with the given login.
	 * @param login The login of the estate agent
	 * @return Estate agent with the given ID or null
	 */
	public EstateAgent getEstateAgentByLogin(String login) {
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		List<EstateAgent> agent;
		agent = session.createQuery(
				"from EstateAgent as estateagent where estateagent.login = login").list();
		session.getTransaction().commit();
		session.close();

		return agent.get(0);
	}
	
	/**
	 * Returns all estateAgents
	 */
	public Set<EstateAgent> getAllEstateAgents() {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		Set<EstateAgent> all = new HashSet<EstateAgent>();
		List<EstateAgent> agentlist;
		agentlist = session.createQuery(
				"from EstateAgent ").list() ;

		for(EstateAgent agent:agentlist){
			all.add(agent);
		}
		session.getTransaction().commit();
		session.close();


		return all;
	}
	
	/**
	 * Find an person with the given id
	 * @param id The ID of the person
	 * @return Person with ID or null
	 */
	public Person getPersonById(int id) {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		Person person = session.get(Person.class, id);
		session.getTransaction().commit();
		session.close();

		return person;
	}
	
	/**
	 * Adds an estate agent
	 * @param ea The estate agent
	 */
	public void addEstateAgent(EstateAgent ea) {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		session.save(ea);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * Deletes an estate agent
	 * @param ea The estate agent
	 */
	public void deleteEstateAgent(EstateAgent ea) {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		session.delete(ea);
		session.getTransaction().commit();
		session.close();

	}
	
	/**
	 * Adds a person
	 * @param p The person
	 */
	public void addPerson(Person p) {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		session.save(p);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * Returns all persons
	 */
	public Set<Person> getAllPersons() {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		Set<Person> all = new HashSet<Person>();
		List<Person> personlist;
		personlist  = session.createQuery(
				"from Person").list() ;

		for(Person person:personlist){
			all.add(person);
		}
		session.getTransaction().commit();
		session.close();


		return all;
	}
	
	/**
	 * Deletes a person
	 * @param p The person
	 */
	public void deletePerson(Person p) {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		session.delete(p);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * Adds a house
	 * @param h The house
	 */
	public void addHouse(House h) {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		session.save(h);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * Returns all houses of an estate agent
	 * @param ea the estate agent
	 * @return All houses managed by the estate agent
	 */
	public Set<House> getAllHousesForEstateAgent(EstateAgent ea) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Set<House> all = new HashSet<House>();
		List<House> houseList;
		houseList  = session.createQuery(
				"from House where manager = manager").list() ;

		for(House house:houseList){
			all.add(house);
		}
		session.getTransaction().commit();
		session.close();


		return all;
	}
	
	/**
	 * Find a house with a given ID
	 * @param  id the house id
	 * @return The house or null if not found
	 */
	public House getHouseById(int id) {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		House house = session.get(House.class, id);
		session.getTransaction().commit();
		session.close();

		return house;	}
	
	/**
	 * Deletes a house
	 * @param h The house
	 */
	public void deleteHouse(House h) {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		session.delete(h);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * Adds an apartment
	 * @param w the aparment
	 */
	public void addApartment(Apartment w) {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		session.save(w);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * Returns all apartments of an estate agent
	 * @param ea The estate agent
	 * @return All apartments managed by the estate agent
	 */
	public Set<Apartment> getAllApartmentsForEstateAgent(EstateAgent ea) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Set<Apartment> all = new HashSet<Apartment>();
		List<Apartment> apartmentList;
		apartmentList  = session.createQuery(
				"FROM Apartment WHERE manager= manager" ).list() ;

		for(Apartment apartment:apartmentList){
			all.add(apartment);
		}
		session.getTransaction().commit();
		session.close();


		return all;
	}
	
	/**
	 * Find an apartment with given ID
	 * @param id The ID
	 * @return The apartment or zero, if not found
	 */
	public Apartment getApartmentByID(int id) {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		Apartment apartment = session.get(Apartment.class, id);
		session.getTransaction().commit();
		session.close();

		return apartment;
	}
	
	/**
	 * Deletes an apartment
	 * @param w The apartment
	 */
	public void deleteApartment(Apartment w) {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		session.delete(w);
		session.getTransaction().commit();
		session.close();
	}
	
	
	/**
	 * Adds a tenancy contract
	 * @param t The tenancy contract
	 */
	public void addTenancyContract(TenancyContract t) {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		session.save(t);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * Adds a purchase contract
	 * @param p The purchase contract
	 */
	public void addPurchaseContract(PurchaseContract p) {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		session.save(p);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * Finds a tenancy contract with a given ID
	 * @param id Die ID
	 * @return The tenancy contract or zero if not found
	 */
	public TenancyContract getTenancyContractByID(int id) {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		TenancyContract TContract = session.get(TenancyContract.class, id);
		session.getTransaction().commit();
		session.close();

		return TContract;
	}
	
	/**
	 * Finds a purchase contract with a given ID
	 * @param id The id of the purchase contract
	 * @return The purchase contract or null if not found
	 */
	public PurchaseContract getPurchaseContractById(int id) {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		PurchaseContract PContract = session.get(PurchaseContract.class, id);
		session.getTransaction().commit();
		session.close();

		return PContract;}
	
	/**
	 * Returns all tenancy contracts for apartments of the given estate agent
	 * @param ea The estate agent
	 * @return All contracts belonging to apartments managed by the estate agent
	 */
	public Set<TenancyContract> getAllTenancyContractsForEstateAgent(EstateAgent ea) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		String id = String.valueOf(ea.getId());
		Set<TenancyContract> all = new HashSet<TenancyContract>();
		List<Integer> tenancyContractList;
		tenancyContractList  = session.createQuery(
				"select id from Apartment as apart  where apart.manager = manager").list() ;

		session.getTransaction().commit();
		session.close();

		Session session2 = sessionFactory.openSession();
		session2.beginTransaction();
		List<TenancyContract> temp = null;
		for(Integer t:tenancyContractList){
			temp = session2.createQuery(
					"from TenancyContract where apartment = apartment").list();
		}

		for(TenancyContract con:temp){
			all.add(con);
		}

		session2.getTransaction().commit();
		session2.close();


		return all;
	}
	
	/**
	 * Returns all purchase contracts for houses of the given estate agent
	 * @param ea The estate agent
	 * @return All purchase contracts belonging to houses managed by the given estate agent
	 */
	public Set<PurchaseContract> getAllPurchaseContractsForEstateAgent(EstateAgent ea) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		String id = String.valueOf(ea.getId());
		Set<PurchaseContract> all = new HashSet<PurchaseContract>();
		List<Integer> tenancyContractList;
		tenancyContractList  = session.createQuery(
				"select id from House as house  where house.manager = manager").list() ;

		session.getTransaction().commit();
		session.close();

		Session session2 = sessionFactory.openSession();
		session2.beginTransaction();
		List<PurchaseContract> temp = null;
		for(Integer t:tenancyContractList){
			temp = session2.createQuery(
					"from PurchaseContract where house = house").list();
		}

		for(PurchaseContract con:temp){
			all.add(con);
		}

		session2.getTransaction().commit();
		session2.close();


		return all;
	}
	
	/**
	 * Finds all tenancy contracts relating to the apartments of a given estate agent	 
	 * @param ea The estate agent
	 * @return set of tenancy contracts

	public Set<TenancyContract> getTenancyContractByEstateAgent(EstateAgent ea) {
		Set<TenancyContract> ret = new HashSet<TenancyContract>();
		Iterator<TenancyContract> it = tenancyContracts.iterator();
		
		while(it.hasNext()) {
			TenancyContract mv = it.next();
			
			if(mv.getApartment().getManager().getId() == ea.getId())
				ret.add(mv);
		}
		
		return ret;
	}
	
	/**
	 * Finds all purchase contracts relating to the houses of a given estate agent
	 * @param  ea The estate agent
	 * @return set of purchase contracts

	public Set<PurchaseContract> getPurchaseContractByEstateAgent(EstateAgent ea) {
		Set<PurchaseContract> ret = new HashSet<PurchaseContract>();
		Iterator<PurchaseContract> it = purchaseContracts.iterator();
		
		while(it.hasNext()) {
			PurchaseContract k = it.next();
			
			if(k.getHouse().getManager().getId() == ea.getId())
				ret.add(k);
		}
		
		return ret;
	}

	**/
	/**
	 * Deletes a tenancy contract
	 * @param tc the tenancy contract
	 */
	public void deleteTenancyContract(TenancyContract tc) {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		session.delete(tc);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * Deletes a purchase contract
	 * @param pc the purchase contract
	 */
	public void deletePurchaseContract(PurchaseContract pc) {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		session.delete(pc);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * Adds some test data
	 */
	public void addTestData() {
		//Hibernate Session erzeugen
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		
		EstateAgent m = new EstateAgent();
		m.setName("Max Mustermann");
		m.setAddress("Am Informatikum 9");
		m.setLogin("max");
		m.setPassword("max");
		
		//TODO: This estate agent is kept in memory and the DB
		//this.addEstateAgent(m);
		session.save(m);
		session.getTransaction().commit();

		session.beginTransaction();

		Person p1 = new Person();
		p1.setAddress("Informatikum");
		p1.setName("Mustermann");
		p1.setFirstname("Erika");


		Person p2 = new Person();
		p2.setAddress("Reeperbahn 9");
		p2.setName("Albers");
		p2.setFirstname("Hans");

		session.save(p1);
		session.save(p2);

		//TODO: These persons are kept in memory and the DB
		session.getTransaction().commit();
		
		
		session.beginTransaction();
		House h = new House();
		h.setCity("Hamburg");
		h.setPostalcode(22527);
		h.setStreet("Vogt-Kölln-Street");
		h.setStreetnumber("2a");
		h.setSquareArea(384);
		h.setFloors(5);
		h.setPrice(10000000);
		h.setGarden(true);
		h.setManager(m);
		
		session.save(h);
		
		//TODO: This house is held in memory and the DB
		session.getTransaction().commit();
		
		// Create Hibernate Session
		session = sessionFactory.openSession();
		session.beginTransaction();
		EstateAgent m2 = (EstateAgent)session.get(EstateAgent.class, m.getId());
		Set<Estate> immos = m2.getEstates();
		Iterator<Estate> it = immos.iterator();
		
		while(it.hasNext()) {
			Estate i = it.next();
			System.out.println("Estate: "+i.getCity());
		}



		Apartment w = new Apartment();
		w.setCity("Hamburg");
		w.setPostalcode(22527);
		w.setStreet("Vogt-Kölln-Street");
		w.setStreetnumber("3");
		w.setSquareArea(120);
		w.setFloor(4);
		w.setRent(790);
		w.setKitchen(true);
		w.setBalcony(false);
		w.setManager(m);
		session.save(w);
		
		w = new Apartment();
		w.setCity("Berlin");
		w.setPostalcode(22527);
		w.setStreet("Vogt-Kölln-Street");
		w.setStreetnumber("3");
		w.setSquareArea(120);
		w.setFloor(4);
		w.setRent(790);
		w.setKitchen(true);
		w.setBalcony(false);
		w.setManager(m);
		session.save(w);


		PurchaseContract pc = new PurchaseContract();
		pc.setPerson(p1);
		pc.setContractPartner(p1);
		pc.setHouse(h);
		pc.setEstate(h);
		pc.setContractNo(9234);
		pc.setDate(new Date(System.currentTimeMillis()));
		pc.setPlace("Hamburg");
		pc.setNoOfInstallments(5);
		pc.setIntrestRate(4);
		session.save(pc);



		TenancyContract tc = new TenancyContract();
		tc.setEstate(w);
		tc.setPerson(p1);
		tc.setApartment(w);
		tc.setContractPartner(p1);
		tc.setContractNo(23112);
		tc.setDate(new Date(System.currentTimeMillis()-1000000000));
		tc.setPlace("Berlin");
		tc.setStartDate(new Date(System.currentTimeMillis()));
		tc.setAdditionalCosts(65);
		tc.setDuration(36);
		session.save(tc);

		session.getTransaction().commit();

		session.close();
	}
}
