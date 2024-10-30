import java.util.Arrays;

/*problem:
* return for each "spell" how many "spell"*"potions[]" that product >= "success" */

/* solutions:
1st - brute force - Time Limit Exceeded
2nd - use binary search
3rd - 2 ptrs*/

public class A3_SuccessfulPairsofSpellsandPotions_2300 {
    public int[] successfulPairs_1_brute_force(int[] spells, int[] potions, long success) {
        int[] ans = new int[spells.length];

        for (int i = 0; i < spells.length; i++) {
            int sum = 0;
            for (int j = 0; j < potions.length; j++) {
                if ((long) spells[i] * potions[j] >= success) {
                    ++sum;
                }
            }
            ans[i] = sum;
        }
//         n==spells, m==potions.
//        time: On*m.
//        space: O1n (spells.len)
        return ans;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    /*motivation:
     * since potions[] sorted, for each "spell" calculate success/curr_spell.
     * binary search for it inside potions[] - and count all elements above it. */

    /*psudo:
     * the ans will be arr at spells.len
     * sort potions[],
     * to get the number we want, each time calculate by: traverse spell[] - and do: "success/curr".
     * all number that are bigger than that index - are good. how we calculate them?
     * we use Binary search to find its index, and then do the "arr.len - index". */
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int[] ans = new int[spells.length];
        Arrays.sort(potions);

        for (int i = 0; i < spells.length; ++i) {
            double x = success / (double) spells[i];//x==all postions[] above x value - would give product above success.
            int x_idx = mybinarysearch(x, potions); //index to x location at potions[].

            ans[i] = potions.length - x_idx;
        }
//         n==spells, m==potions.
//        time: O(n+m * log m): o m log m - sort. o1n- traverse and inside each traverse: O log m - binary search target.
//        spcae:O log n(+plus each language use different space for sort)
        return ans;
    }

    private int mybinarysearch(double x, int[] potions) { //note-want to take 1 index before x!!
        int l = 0;
        int r = potions.length - 1;

        while (l <= r) {
            int mid = l + ((r - l) / 2);

//            if use it - wont pass testing! - since it give the idx to x - want 1 before x!!!
//            if (potions[mid] == x){
//                return (int)x;
//            }

            if (potions[mid] >= x) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

/*motivation: use "trick" for optimize speed, after sort both spells and potions,
* traverse with once ptr from end of potions, for the weakest spell ptrs slighlt moove and from there will
* move a litlle till reach biggest spell.*/
    public int[] successfulPairs_2_ptrs(int[] spells, int[] potions, long success){
        int[] ans = new int[spells.length];

        // sortSpell in format: <value ,index> (why need index? for later know order to put them in ans)
        int[][] sortedSpells = new int[spells.length][2];
        for (int i = 0; i < spells.length; i++) {
            sortedSpells[i][0] = spells[i];
            sortedSpells[i][1] = i;
        }

//        sort both in increasing order:
        Arrays.sort(sortedSpells, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(potions);


        int potionIndex = potions.length - 1;
        for (int[] sortedSpell : sortedSpells) {
            int spell = sortedSpell[0];
            int index = sortedSpell[1];

            while (potionIndex >= 0 && (long)spell * potions[potionIndex] >= success) {
                potionIndex -= 1;
            }
            ans[index] = potions.length - (potionIndex + 1);
        }
//         n==spells, m==potions.
//        time: On logn + O m log m: O1n-sortspell[], Om+n(log n+m) sort both. On+m-traverse all possibilities.
//        space: On, log m: sort spells, index[]. log m - java Quicksort m.
        return ans;
    }
}