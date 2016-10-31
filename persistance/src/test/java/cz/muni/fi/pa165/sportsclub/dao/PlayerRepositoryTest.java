package cz.muni.fi.pa165.sportsclub.dao;

import cz.muni.fi.pa165.sportsclub.EntityFactory;
import cz.muni.fi.pa165.sportsclub.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.sportsclub.entity.Player;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

/**
 * @author Fabian Norbert
 */

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PlayerRepositoryTest extends AbstractTestNGSpringContextTests {

    @Inject
    private PlayerDao playerDao;

    private EntityFactory entityFactory = new EntityFactory();

    @DataProvider
    public static Object[][] createPlayerDataProvider() {
        return new Object[][] {
                {190, 100},
                {null, 100},
                {190, null}
        };
    }

    @Test(dataProvider = "createPlayerDataProvider")
    public void createPlayerTest(Integer height, Integer weight) {
        Player player = entityFactory.createPlayer(height, weight);
        playerDao.create(player);

        Assert.assertNotNull(player.getId());

        Player persistedPlayer = playerDao.findById(player.getId());

        Assert.assertEquals("firstName", persistedPlayer.getFirstName());
        Assert.assertEquals("lastName", persistedPlayer.getLastName());
        Assert.assertEquals(player.getDateOfBirth(), persistedPlayer.getDateOfBirth());
        Assert.assertEquals(height != null ? height.intValue() : 0, persistedPlayer.getHeight());
        Assert.assertEquals(weight != null ? weight.intValue() : 0, persistedPlayer.getWeight());
    }

    @Test
    public void updatePlayerTest() {
        Player player = entityFactory.createPersistedPlayer(playerDao);
        player.setLastName("updatedLastName")
                .setFirstName("updatedFirstName")
                .setDateOfBirth(entityFactory.getDate())
                .setWeight(10)
                .setHeight(10);

        playerDao.update(player);

        Player persistedPlayer = playerDao.findById(player.getId());

        Assert.assertEquals(player.getFirstName(), persistedPlayer.getFirstName());
        Assert.assertEquals(player.getLastName(), persistedPlayer.getLastName());
        Assert.assertEquals(player.getDateOfBirth(), persistedPlayer.getDateOfBirth());
        Assert.assertEquals(player.getHeight(), persistedPlayer.getHeight());
        Assert.assertEquals(player.getWeight(), persistedPlayer.getWeight());
    }

    @Test
    public void listAllPlayersTest() {
        entityFactory.createPersistedPlayer("firstPlayer", playerDao);
        entityFactory.createPersistedPlayer("secondPlayer", playerDao);
        entityFactory.createPersistedPlayer("thirdPlayer", playerDao);

        Assert.assertEquals(3, playerDao.findAll().size());
    }

    @Test
    public void deletePlayerTest() {
        Player player = entityFactory.createPersistedPlayer(playerDao);
        playerDao.remove(player);
        Assert.assertTrue(playerDao.findAll().isEmpty());
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNullFirstName() {
        Player player = entityFactory.createPlayer();
        player.setFirstName(null);
        playerDao.create(player);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNullLastName() {
        Player player = entityFactory.createPlayer();
        player.setLastName(null);
        playerDao.create(player);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNullDateOfBirth() {
        Player player = entityFactory.createPlayer();
        player.setDateOfBirth(null);
        playerDao.create(player);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testHeightNotNegative() {
        Player player = entityFactory.createPlayer();
        player.setHeight(-1);
        playerDao.create(player);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testWeightNotNegative() {
        Player player = entityFactory.createPlayer();
        player.setWeight(-1);
        playerDao.create(player);
    }

}
