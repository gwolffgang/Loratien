package de.shazzarr.loratien.server.interfaces.filter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import de.shazzarr.loratien.server.bean.player.Player;

public interface PlayerFilter {
	Predicate toPredicate(Root<Player> root, CriteriaQuery<Player> cq, CriteriaBuilder cb);
}
