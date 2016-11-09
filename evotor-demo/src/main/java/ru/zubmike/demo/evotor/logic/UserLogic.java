package ru.zubmike.demo.evotor.logic;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.zubmike.demo.evotor.dao.UserDAO;
import ru.zubmike.demo.evotor.types.domain.User;
import ru.zubmike.demo.evotor.types.dto.BalanceResponse;
import ru.zubmike.demo.evotor.types.dto.RequestType;
import ru.zubmike.demo.evotor.types.dto.ResponseCode;
import ru.zubmike.demo.evotor.types.dto.SimpleResponse;
import ru.zubmike.demo.evotor.types.dto.UserRequest;
import ru.zubmike.demo.evotor.utils.DataSourceException;
import ru.zubmike.demo.evotor.utils.DuplicateUserException;

public class UserLogic {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserLogic.class);

	private static final double DEFAULT_BALANCE = 0;

	private final UserDAO userDAO;

	@Inject
	public UserLogic(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public Object executeRequest(UserRequest request) {
		String requestTypeStr = request.getRequestType();
		if (requestTypeStr != null) {
			RequestType requestType = RequestType.from(requestTypeStr.toUpperCase());
			if (requestType != null) {
				switch (requestType) {
				case CREATE_USER:
					return createUser(request);
				case GET_BALANCE:
					return getBalance(request);
				}
			}
		}
		return new SimpleResponse(ResponseCode.INTERNAL_ERROR.getValue());
	}

	private SimpleResponse createUser(UserRequest request) {
		try {
			User user = userDAO.getUser(request.getLogin());
			if (user == null) {
				addUser(request);
				return new SimpleResponse(ResponseCode.OK.getValue());
			} else {
				return new SimpleResponse(ResponseCode.USER_ALREADY_EXISTS.getValue());
			}
		} catch (DuplicateUserException e) {
			return new SimpleResponse(ResponseCode.USER_ALREADY_EXISTS.getValue());
		} catch (DataSourceException e) {
			LOGGER.error(e.getMessage(), e);
			return new SimpleResponse(ResponseCode.INTERNAL_ERROR.getValue());
		}
	}

	private void addUser(UserRequest request) throws DataSourceException, DuplicateUserException {
		User user = new User(request.getLogin(), request.getPassword(), DEFAULT_BALANCE);
		userDAO.addUser(user);
	}

	private BalanceResponse getBalance(UserRequest request) {
		try {
			User user = userDAO.getUser(request.getLogin());
			if (user != null) {
				return checkPassword(request, user)
						? new BalanceResponse(ResponseCode.OK.getValue(), user.getBalance())
						: new BalanceResponse(ResponseCode.WRONG_PASSWORD.getValue());
			} else {
				return new BalanceResponse(ResponseCode.USER_NOT_EXISTS.getValue());
			}
		} catch (DataSourceException e) {
			LOGGER.error(e.getMessage(), e);
			return new BalanceResponse(ResponseCode.INTERNAL_ERROR.getValue());
		}
	}

	private boolean checkPassword(UserRequest request, User user) {
		return user.getPassword() != null && user.getPassword().equals(request.getPassword());
	}

}
