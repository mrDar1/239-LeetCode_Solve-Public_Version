import java.util.*;

/*problem:
* return 2 Lists:
* 1-all players with 0 loses.
* 2-all players with 1 loss.
*
* data given as: matches[i] = [winner i, loser i]*/

/* solutions:
1st: 3 hashsets - simple but lots of minor edges..
2nd: seen hashset + count_losses hashmap
3rd: 1 count_losses hashmap                 -     my favorite!!!!
4th: use arr - for practice, pretty much the same, and adding for fun losers of 2 matches.*/

//note: map.get(x) . x-must be keys! and return only values! to return key we need some manipulation.

public class A12_FindPlayersWithZeroOrOneLossess_225 {
    public static void main(String[] args){
        int[][] arr = {{1,3},{2,3},{3,6},{5,6},{5,7},{4,5},{4,8},{4,9},{10,4},{10,9}};
        System.out.println(findWinners1(arr));
        System.out.println(findWinners2(arr));
        System.out.println(findWinners3(arr));
        System.out.println(findWinners4(arr));
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static List<List<Integer>> findWinners1(int[][] matches) {
//   1st solution - 3 hashsets:
//intuition: use 3 hashsets - each round update player to the relevant Set
        Set<Integer> zeroLoss = new HashSet<>();
        Set<Integer> oneLoss = new HashSet<>();
        Set<Integer> moreLosses = new HashSet<>();

        for (int[] match : matches) {
            int winner = match[0];
            int loser = match[1];
            // Add winner.
            if ( !oneLoss.contains(winner) && !moreLosses.contains(winner)) {
                zeroLoss.add(winner);
            }
            // Add or move loser.
            if (zeroLoss.contains(loser)) {
                zeroLoss.remove(loser);
                oneLoss.add(loser);
            }
            else if (oneLoss.contains(loser)) {
                oneLoss.remove(loser);
                moreLosses.add(loser);
            }
            else if (moreLosses.contains(loser)) {
                continue;
            }
            else {
                oneLoss.add(loser);
            }
        }

        List<List<Integer>> answer = Arrays.asList(new ArrayList<>(), new ArrayList<>());
        answer.get(0).addAll(zeroLoss);
        answer.get(1).addAll(oneLoss);
        Collections.sort(answer.get(0));
        Collections.sort(answer.get(1));

        return answer;
        //        time: O n log n
        //        space: On
    }


    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    //solution 2 - hashmap + hashset:
    public static List<List<Integer>> findWinners2(int[][] matches) {
/*intuition: add all players to seen Hashset, and count losses in hashmap <player number, loss counter>.
* traverse all seen players - if player do not contain at loss_count Hashmap - he got 0 losses add to ans[0].
* if this player got exactly 1 loss - add ans[1] */
        Map<Integer, Integer> lossesCount = new HashMap<>();
        Set<Integer> seen = new HashSet<>();

        for (int[] match : matches) {
            int winner = match[0];
            int loser = match[1];

            seen.add(winner);
            seen.add(loser);
            lossesCount.put(loser, lossesCount.getOrDefault(loser, 0) + 1);
        }

        // Add players with 0 or 1 loss to the corresponding list.
        List<List<Integer>> answer = Arrays.asList(new ArrayList<>(), new ArrayList<>());
        for (int player : seen) {
            if ( !lossesCount.containsKey(player) ) {
                answer.get(0).add(player);
            }
            else if (lossesCount.get(player) == 1) {
                answer.get(1).add(player);
            }
        }

        Collections.sort(answer.get(0));
        Collections.sort(answer.get(1));

        return answer;
        //        time: O n log n
        //        space: On
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static List<List<Integer>> findWinners3(int[][] matches) {
//3rd: 1 hashmap:
/*psudo:
* traverse matches[][] - and count at Hashmap loss_count<player, number of losses>
* add winner with 0 loss (so he will be "seen")
* add add loser +1
* traveser hashmap and if number_of_losses==0 add to winner,
* if number_of_losses==1 add to 1 loss. */

        HashMap<Integer, Integer> count_losses = new HashMap<>(); //<index, count>
        List<List<Integer>> ans = Arrays.asList(new ArrayList<>(), new ArrayList<>() ); //first is 0 losses, secoond is 1 loss.

//        count number of losses to each player:
        for (int[] arr : matches){
            int winner = arr[0];
            int loser  = arr[1];

            count_losses.put(winner, count_losses.getOrDefault(winner, 0)); //count only losers - this is why we dont count winning with +1. but in this way they wiil be 'seen' - so we know they play on the tournament.
            count_losses.put(loser, count_losses.getOrDefault(loser, 0) + 1);
            }

        for(int player : count_losses.keySet() ){
            if(count_losses.get(player) == 0){
                ans.get(0).add(player);
            }
            if(count_losses.get(player) == 1){
                ans.get(1).add(player);
            }
        }

        Collections.sort(ans.get(0));
        Collections.sort(ans.get(1));

        //        time: O2n + n logn: 1st-count and build hashmap. 2nd-traverse hashmap and add to ans. nlogn-sort.
        //        space: On - number of players in tournament.
        return ans;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    //   4th solution - arr:
    public static List<List<Integer>> findWinners4(int[][] matches) {
        int[] lossesCount = new int[100001]; //constraints: largest sum of input.
        Arrays.fill(lossesCount, -1);

        for (int[] match : matches) {
            int winner = match[0];
            int loser = match[1];

            if (lossesCount[winner] == -1) {
                lossesCount[winner] = 0;
            }
            if (lossesCount[loser] == -1) {
                lossesCount[loser] = 1;
            } else {
                lossesCount[loser]++;
            }
        }
//        for fun: for printing the players who got 2 losses do this:
        List<List<Integer>> answer = Arrays.asList(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        for (int i = 1; i < 100001; ++i) {
            if (lossesCount[i] == 0){
                answer.get(0).add(i);
            }
            else if (lossesCount[i] == 1) {
                answer.get(1).add(i);
            }
            else if (lossesCount[i] == 2) {
                answer.get(2).add(i);
            }
        }
        return answer;
        //        time: O n log n
        //        space: On
    }
}