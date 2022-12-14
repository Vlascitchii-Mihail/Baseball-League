package dev.mfazio.androidbaseballleague.standings

import android.icu.text.DecimalFormat
import dev.mfazio.androidbaseballleague.team.UITeam

/**
 * Information about the teams for the Recycler View's item
 */
class UITeamStanding(
    val teamStanding: TeamStanding,
    val teamName: String,
    val gamesBack: Double,
    val placeInLeague: Int,
    val placeInDivision: Int
) {
    private val winPercentageNumber = teamStanding.wins / (teamStanding.wins + teamStanding.losses).toDouble()
    private val lastTenNumber = minOf(10 - teamStanding.winsLastTeam, teamStanding.losses)

    val teamId = teamStanding.teamId
    val wins = teamStanding.wins.toString()
    val losses = teamStanding.losses.toString()

    //formation the Double number
    val winPercentage = winPercentageFormat.format(winPercentageNumber)
    val winLossText = "${teamStanding.wins} - ${teamStanding.losses}"
    val gamesBackText = if (gamesBack <= 0) "-" else gamesBack.toString()
    val lastTenText = " ${teamStanding.winsLastTeam} - $lastTenNumber"
    val streakText = "${teamStanding.streakType.shortName}${teamStanding.streakCount}"

    companion object {

        //Creates a DecimalFormat based on the given pattern, using symbols for the default locale.
        private val winPercentageFormat = DecimalFormat("#.000")

        fun fromTeamIdAndStandings(teamId: String?, teamStandings: List<TeamStanding>) =
            UITeam.fromTeamId(teamId)?.let { uiTeam ->
                fromTeamAndStandings(uiTeam, teamStandings)
            }


        fun fromTeamAndStandings(team: UITeam, teamStandings: List<TeamStanding>) =
            teamStandings.firstOrNull { it.teamId == team.teamId} ?.let { teamStanding ->
                UITeamStanding(
                    teamStanding,
                    team.teamName,
                    teamStanding.divisionGamesBack,
                    teamStandings.sortedBy { it.leagueGamesBack }.indexOf(teamStanding) + 1,
                    teamStandings.filter { it.division == team.division }.sortedBy { it.divisionGamesBack }
                        .indexOf(teamStanding) + 1
                )
            }
    }
}