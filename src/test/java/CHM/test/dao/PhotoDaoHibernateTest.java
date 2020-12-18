package CHM.test.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import CHM.config.AppConfig;
import CHM.dao.PhotoDaoHibernate;
import CHM.model.Photo;
import CHM.model.Profile;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes= AppConfig.class)
public class PhotoDaoHibernateTest {
	
	@Autowired
	private PhotoDaoHibernate photoDaoHibernate;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Mock
	private SessionFactory mockSessionFactory;
	
	@Mock
	private Session mockSession;
	
	private Session sess;
	
	private Session spy;
	
	private Photo photo;
	
	private Profile profile = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		photo = new Photo(101, null, profile);
		photoDaoHibernate.insertPhoto(photo);
		
		MockitoAnnotations.initMocks(this);
		
		// Make a real session
		sess = sessionFactory.openSession();
		
		// Spy on that real session
		spy = Mockito.spy(sess);
		
		// Mock session factory to always return the spied on session 
		when(mockSessionFactory.openSession()).thenReturn(spy);
		
		// Set photoDao to use that mocked session factory 
		photoDaoHibernate.setSessionFactory(mockSessionFactory);
		
	}

	@After
	public void tearDown() throws Exception {
		
		photoDaoHibernate.setSessionFactory(sessionFactory);
		try {
			photoDaoHibernate.deletePhoto(photo);
		} catch (Exception e) {
			
		} finally {
			if (sess.isOpen()) {
				sess.close();
			}
		}
		
	}

	@Test
	public void testSelectPhoto() {
		try {
			photoDaoHibernate.selectPhoto(photo.getPhotoId());
			verify(spy).get(Photo.class, photo.getPhotoId());
			verify(spy).close();
		} catch (HibernateException e) {
			fail("HibernateException " + e);
		}
	}
	
	@Test
	@Rollback(true)
	public void testInsertPhoto() {
		
		try {
			photoDaoHibernate.insertPhoto(photo);
			verify(spy).save(photo);
			verify(spy).close();
		} catch (HibernateException e) {
			fail("HibernateException: " + e);
		}
	}
	
	@Test
	@Rollback(true)
	public void testSelectAllPhotos() {
		try {
			photoDaoHibernate.selectAllPhotos();
			verify(spy).getCriteriaBuilder();
			verify(spy).close();
		} catch (HibernateException e) {
			fail("Excepton " + e);
		}
	}
	
	@Test
	@Rollback(true)
	public void testUpdatePhoto() {
		try {
			photoDaoHibernate.updatePhoto(photo);
			verify(spy).update(photo);
			verify(spy).close();
		} catch (HibernateException e) {
			fail("Excepton " + e);
		}
	}
	
	@Test
	@Rollback(true)
	public void testDeletePhoto() {
		try {
			photoDaoHibernate.deletePhoto(photo);
			verify(spy).delete(photo);
			verify(spy).close();
		} catch (Exception e) {
			fail("Excepton " + e);
		}
	}

}
