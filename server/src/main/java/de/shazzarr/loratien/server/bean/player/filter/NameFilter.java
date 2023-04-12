package de.shazzarr.loratien.server.bean.player.filter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import de.shazzarr.loratien.server.bean.player.Player;
import de.shazzarr.loratien.server.interfaces.filter.PlayerFilter;

public class NameFilter implements PlayerFilter {

	String name;

	public NameFilter(String name) {
		this.name = name;
	}

	@Override
	public Predicate toPredicate(Root<Player> root, CriteriaQuery<Player> cq, CriteriaBuilder cb) {
		return cb.equal(root.get("name"), name);
	}
}