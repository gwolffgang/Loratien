package de.shazzarr.loratien.server.interfaces.repository;

import java.util.List;

import de.shazzarr.loratien.server.bean.player.Player;
import de.shazzarr.loratien.server.exception.NameInUseException;
import de.shazzarr.loratien.server.exception.PlayerNotFoundException;
import de.shazzarr.loratien.server.exception.RegistrationFailureException;

public interface PlayerRepository {

	List<Player> getAll();

	Player getByUuid(String uuid) throws PlayerNotFoundException;

	Player getByName(String name) throws PlayerNotFoundException;

	Player put(Player player) throws PlayerNotFoundException;

	boolean delete(Player player);

	Player register(String name) throws NameInUseException, RegistrationFailureException;
}