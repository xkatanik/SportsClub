package cz.muni.fi.pa165.sportsclub.facade;

import cz.muni.fi.pa165.sportsclub.EntityFactoryService;
import cz.muni.fi.pa165.sportsclub.dto.team.TeamDto;
import cz.muni.fi.pa165.sportsclub.entity.Team;
import cz.muni.fi.pa165.sportsclub.service.TeamService;
import org.dozer.Mapper;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by norbert on 19.11.16.
 */

public class TeamFacadeTest {

    @Mock
    private TeamService teamService;

    @Mock
    private Mapper dtoMapper;

    @InjectMocks
    private TeamFacade teamFacade = new TeamFacadeImpl();

    private EntityFactoryService entityFactoryService = new EntityFactoryService();

    @BeforeClass
    public void setUpClass() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUpMethod() {
        Mockito.when(dtoMapper.map(Mockito.any(), Mockito.any()))
                .thenReturn(entityFactoryService.createTeamDto());
    }


    @Test
    public void getTeamTest() {
        Mockito.when(teamService.findById(Mockito.anyLong()))
                .thenReturn(entityFactoryService.createTeam());

        TeamDto result = teamFacade.getTeam(1L);

        Mockito.verify(teamService, Mockito.times(1)).findById(Mockito.anyLong());
        Assert.assertNotNull(result);
    }

    @Test
    public void getAllTeamsTest() {
        List<Team> teams = new ArrayList<>();
        teams.add(entityFactoryService.createTeam("Team1"));
        teams.add(entityFactoryService.createTeam("Team2"));
        Mockito.when(teamService.getAll()).thenReturn(teams);

        teamFacade.getAllTeams();

        Mockito.verify(teamService, Mockito.times(1)).getAll();
    }

    @Test
    public void createTeamTest() {
        Mockito.when(dtoMapper.map(Mockito.any(), Mockito.any()))
                .thenReturn(entityFactoryService.createTeam());

        teamFacade.createTeam(entityFactoryService.createTeamCreateDto());

        Mockito.verify(teamService, Mockito.times(1)).createTeam(Mockito.any(Team.class));
    }

    @Test
    public void deleteTeamTest() {
        teamFacade.deleteTeam(1);

        Mockito.verify(teamService, Mockito.times(1)).removeTeam(Mockito.anyLong());
    }

    @Test
    public void updateTeamTest() {
        teamFacade.updateTeam(entityFactoryService.createTeamDto());

        Mockito.verify(teamService, Mockito.times(1)).updateTeam(Mockito.any(Team.class));
    }

}