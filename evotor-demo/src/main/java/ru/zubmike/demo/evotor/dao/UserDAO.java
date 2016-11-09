package ru.zubmike.demo.evotor.dao;

import ru.zubmike.demo.evotor.types.domain.User;
import ru.zubmike.demo.evotor.utils.DataSourceException;
import ru.zubmike.demo.evotor.utils.DuplicateUserException;

public interface UserDAO {

	int addUser(User user) throws DataSourceException, DuplicateUserException;

	User getUser(String login) throws DataSourceException;

}
