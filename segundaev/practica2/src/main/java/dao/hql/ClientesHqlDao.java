package dao.hql;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import dao.IDao;
import exceptions.ExcepcionCodigoYaExistente;
import model.Cliente;


public class ClientesHqlDao implements IDao<Cliente> {

    private Session session;

    public ClientesHqlDao(Session session) {
        this.session = session;
    }

    @Override
    public Optional<Cliente> uno(Object id) {
        return Optional.ofNullable(session.get(Cliente.class, (Integer) id));
    }

    @Override
    public List<Cliente> todos() {
        return session.createQuery("from Cliente", Cliente.class).list();
    }

    @Override
    public void guardar(Cliente t) throws ExcepcionCodigoYaExistente {
        try {
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new ExcepcionCodigoYaExistente("a");
        }
    }

    @Override
    public void modificar(Cliente t) {
        session.beginTransaction();
        session.update(t);
        session.getTransaction().commit();
    }

    @Override
    public void eliminar(Cliente t) {
        session.delete(t);
    }
    
}
