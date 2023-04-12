package de.shazzarr.loratien.server.bean.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import de.shazzarr.loratien.server.bean.player.filter.NameFilter;
import de.shazzarr.loratien.server.exception.NameInUseException;
import de.shazzarr.loratien.server.exception.PlayerNotFoundException;
import de.shazzarr.loratien.server.exception.RegistrationFailureException;
import de.shazzarr.loratien.server.interfaces.filter.PlayerFilter;
import de.shazzarr.loratien.server.interfaces.repository.PlayerRepository;

public class PlayerHibernateH2RepositoryImpl implements PlayerRepository {

	private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("main");
	private final EntityManager em = entityManagerFactory.createEntityManager();
	private final EntityTransaction transaction = em.getTransaction();

	@Override
	public List<Player> getAll() {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Player> cq = cb.createQuery(Player.class);
		final Root<Player> rootEntry = cq.from(Player.class);
		final CriteriaQuery<Player> all = cq.select(rootEntry);
		final TypedQuery<Player> allQuery = em.createQuery(all);
		return allQuery.getResultList();
	}

	@Override
	public Player getByUuid(String uuid) throws PlayerNotFoundException {
		try {
			transaction.begin();
			final Player player = em.find(Player.class, uuid);
			transaction.commit();
			return Optional.ofNullable(player).get();
		} catch (final Exception e) {
			e.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new PlayerNotFoundException("Player not found by UUID : " + uuid);
		}
	}

	@Override
	public Player getByName(String name) throws PlayerNotFoundException {
		final List<Player> playersFound = findByFilter(new NameFilter(name));
		if (playersFound.size() == 1) {
			return playersFound.get(0);
		}
		throw new PlayerNotFoundException("Player not found by name : " + name);
	}

	@Override
	public Player put(Player player) throws PlayerNotFoundException {
		try {
			transaction.begin();
			em.persist(player);
			transaction.commit();
			return getByUuid(player.getUuid());
		} catch (final IllegalStateException e) {
			e.printStackTrace();
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		}
		return null;
	}

	@Override
	public boolean delete(Player player) {
		try {
			transaction.begin();
			em.remove(em.contains(player) ? player : em.merge(player));
			transaction.commit();
			getByUuid(player.getUuid());
			return false;
		} catch (final PlayerNotFoundException e) {
			return true; // all fine!
		}
	}

	@Override
	public Player register(String name) throws RegistrationFailureException {
		try {
			getByName(name);
			throw new NameInUseException("The name is already in use : " + name);
		} catch (final PlayerNotFoundException pnfe1) {
			try {
				return put(new Player(name));
			} catch (final PlayerNotFoundException pnfe2) {
				pnfe2.printStackTrace();
				throw new RegistrationFailureException("Player could not be added to database : " + name);
			}
		}
	}

	public List<Player> findByFilter(PlayerFilter... filterArray) {
		final CriteriaBuilder cbuilder = em.getCriteriaBuilder();
		final CriteriaQuery<Player> cquery = cbuilder.createQuery(Player.class);
		final Root<Player> playerRoot = cquery.from(Player.class);
		cquery.select(playerRoot);
		final List<Predicate> predicates = new ArrayList<>();
		for (final PlayerFilter filter : filterArray) {
			predicates.add(filter.toPredicate(playerRoot, cquery, cbuilder));
		}
		cquery.where(predicates.toArray(new Predicate[predicates.size()]));

		final TypedQuery<Player> query = em.createQuery(cquery);
		return query.getResultList();
	}

}
