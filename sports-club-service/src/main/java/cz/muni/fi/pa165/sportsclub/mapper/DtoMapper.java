package cz.muni.fi.pa165.sportsclub.mapper;

import java.util.Collection;
import java.util.List;

import cz.muni.fi.pa165.sportsclub.dto.membership.MembershipDto;
import cz.muni.fi.pa165.sportsclub.dto.player.PlayerDto;
import cz.muni.fi.pa165.sportsclub.dto.team.TeamDto;
import cz.muni.fi.pa165.sportsclub.dto.teamManager.TeamManagerDto;
import cz.muni.fi.pa165.sportsclub.entity.Membership;
import cz.muni.fi.pa165.sportsclub.entity.Player;
import cz.muni.fi.pa165.sportsclub.entity.Team;
import cz.muni.fi.pa165.sportsclub.entity.TeamManager;

/**
 * @author Fabian Norbert
 */
public interface DtoMapper {

    /**
     * Maps collection of objects to given class
     *
     * @param objects Collection of objects to map
     * @param mapToClass Class type of the destination class
     * @param <T> Destination class type
     * @return List of mapped objects
     */
    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    /**
     * Maps object to given class
     *
     * @param object Object to map to the destination object
     * @param mapToClass Class type of the destination object
     * @param <T> Class type
     * @return Mapped object
     */
    <T> T mapTo(Object object, Class<T> mapToClass);

    /**
     * Maps fields with same name from one class to another.
     *
     * @param source Source class
     * @param destination Destination class
     */
    void mapTo(Object source, Object destination);

    /**
     * Maps a team entity to a dto of given type.
     *
     * @param team Team entity
     * @return Mapped TeamDto
     */
    TeamDto teamToDto(Team team);

    MembershipDto membershipToDto(Membership membership);

    Membership dtoToMembership(MembershipDto dto);


    /**
     * Maps a teamDto to an entity.
     * @param dto Dto to map
     * @return Mapped team entity
     */
    Team dtoToTeam(TeamDto dto);

    /**
     * Maps a team manager DTO to a an entity
     * @param dto DTO to map
     * @return Mapped team manager entity
     */
    TeamManager dtoToTeamManager(TeamManagerDto dto);

    /**
     * Maps a team manager entity to a dto of type TeamManagerDto
     * @param tm Team manager entity
     * @return Mapped dto
     */
    TeamManagerDto teamManagerToDto(TeamManager tm);

    Player dtoToPlayer(PlayerDto dto);

    PlayerDto playerToDto(Player player);
}