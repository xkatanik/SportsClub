package cz.muni.fi.pa165.sportsclub.service;

import cz.muni.fi.pa165.sportsclub.EntityFactoryService;
import cz.muni.fi.pa165.sportsclub.dao.TeamManagerDao;
import cz.muni.fi.pa165.sportsclub.entity.TeamManager;
import cz.muni.fi.pa165.sportsclub.service.impl.TeamManagerServiceImpl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.service.spi.ServiceException;

/**
 *
 * @author Marian Sulgan
 */
public class TeamManagerServiceTest {
    
    @Mock
    private TeamManagerDao tmDao;
    
    @InjectMocks
    private TeamManagerService tmService = new TeamManagerServiceImpl();
    
    private EntityFactoryService entityFactoryService = new EntityFactoryService();
    
    @BeforeClass
    public void setUpClass() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void createTeamManagerTest() {
        tmService.createTeamManager(entityFactoryService.createTeamManager());
        Mockito.verify(tmDao, Mockito.times(1)).create(Mockito.any());
    }
    
    @Test
    public void removeTeamManagerTest() {
        TeamManager tm = entityFactoryService.createTeamManager();
        tmService.removeTeamManager(tm.getId());
        Mockito.verify(tmDao, Mockito.times(1)).remove(Mockito.anyLong());
    }
    
    @Test
    public void updateTeamManagerTest() {
        TeamManager tm = entityFactoryService.createTeamManager();
        Mockito.when(tmDao.update(Mockito.any())).thenReturn(tm);
        TeamManager test = tmService.updateTeamManager(tm);
        Mockito.verify(tmDao, Mockito.times(1)).update(Mockito.any());
        Assert.assertEquals(tm, test);
    }
    
    @Test
    public void findByIdTeamManagerTest() {
        Mockito.when(tmDao.findById(Mockito.anyLong()))
                .thenReturn(entityFactoryService.createTeamManager());

        TeamManager result = tmService.findById(1L);

        Mockito.verify(tmDao, Mockito.times(1)).findById(Mockito.anyLong());
        Assert.assertNotNull(result);
    }
    
    @Test
    public void findByNameTeamManagerTest() {
        List<TeamManager> res = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            res.add(entityFactoryService.createTeamManager());
        Mockito.when(tmDao.findByName(Mockito.anyString())).thenReturn(res);
        
        List<TeamManager> test = tmService.findByName("John Doe");
        
        Mockito.verify(tmDao, Mockito.times(1)).findByName(Mockito.anyString());
        Assert.assertNotNull(test);
        Assert.assertEquals(3, test.size());
    }
    
    @Test
    public void getAllTeamManagerTest() {
        List<TeamManager> res = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            res.add(entityFactoryService.createTeamManager());
        Mockito.when(tmDao.getAll()).thenReturn(res);

        List<TeamManager> test = tmService.getAll();

        Mockito.verify(tmDao, Mockito.times(1)).getAll();
        Assert.assertNotNull(test);
        Assert.assertEquals(3, test.size());
    }
    
}