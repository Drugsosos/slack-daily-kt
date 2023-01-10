import org.hibernate.HibernateException
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Transaction
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.Configuration

/**
 * Database query
 * (for lambda calls w/o session & transaction management)
 *
 * @param session Opened session
 * @param transaction Started transaction instance
 */
fun interface DefaultQuery<session : Session, transaction : Transaction> {
    @Throws(HibernateException::class)
    fun execute(session: session, transaction: transaction)
}

fun interface DatabaseQuery :
    DefaultQuery<Session, Transaction>

/**
 * Default Database class with established connection and transaction management
 */
open class BaseDB {
    private val configuration: Configuration = Configuration().configure()
    private val serviceRegistry: StandardServiceRegistryBuilder =
        StandardServiceRegistryBuilder().applySettings(configuration.properties)
    private val sessionFactory: SessionFactory = configuration.buildSessionFactory(serviceRegistry.build())

    fun executeWithTransaction(
        handler: DatabaseQuery
    ) {
//        TODO: if openSession throws something - RIP
        val session: Session = sessionFactory.openSession()
        val transaction: Transaction = session.beginTransaction()

        var results = Unit

        try {
            results = handler.execute(session, transaction)
        } catch (e: HibernateException) {
            logger.error { "DB call failed with: $e" }
        } finally {
            transaction.commit()
            session.close()
        }
        return results
    }
}
