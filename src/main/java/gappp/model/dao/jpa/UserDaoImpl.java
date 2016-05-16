package gappp.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gappp.model.User;
import gappp.model.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUser( Integer id )
    {
        return entityManager.find( User.class, id );
    }

    @Override
    public List<User> getUsers()
    {
        return entityManager.createQuery( "from User order by id", User.class )
            .getResultList();
    }

	@Override
	@Transactional
	public User saveUser(User user) {
		return entityManager.merge(user);
	}

	@Override
	public boolean getUser(String email) {
		List<User> users = entityManager.createQuery( "from User where lower(eMail) = :email", User.class )
				.setParameter("email", email).getResultList();
		if(users.size() == 0)
			return false;
		else
			return true;
	}

	@Override
	public User getUser(String email, String password) {
		List<User> user = entityManager.createQuery( "from User where eMail = :email and password = :password", User.class )
							.setParameter("email", email)
							.setParameter("password", password)
							.getResultList();

		if(user.isEmpty())
			return null;
		else
			return user.get(0);
	}

	@Override
	public User getUserByEmail(String email) {
		return entityManager.createQuery( "from User where lower(eMail) = :email", User.class )
				.setParameter("email", email).getSingleResult();
	}

}
