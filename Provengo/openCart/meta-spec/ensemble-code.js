// @provengo summon ctrl

/**
 * List of events "of interest" that we want test suites to cover.
 */
const GOALS = [
    Ctrl.markEvent('End(UserLogIn)'),
    Ctrl.markEvent('End(UserSearchProduct)'),
    Ctrl.markEvent('End(UserAddToCart)'),
    Ctrl.markEvent('End(UserNavigatesToCheckout)'),
    Ctrl.markEvent('End(AdminLogsIn)'),
    Ctrl.markEvent('End(AdminNavigatesToProductsPage)'),
    Ctrl.markEvent('End(AdminFiltersProduct)'),
    Ctrl.markEvent('End(AdminEditsProductsPrice)')
];

const GOALS_2WAY = [
    // same event
    [Ctrl.markEvent('End(UserLogIn)'), Ctrl.markEvent('End(UserSearchProduct)')],
    [Ctrl.markEvent('End(UserSearchProduct)'), Ctrl.markEvent('End(UserAddToCart)')],
    [Ctrl.markEvent('End(UserAddToCart)'), Ctrl.markEvent('End(UserNavigatesToCheckout)')],
    [Ctrl.markEvent('End(AdminLogsIn)'), Ctrl.markEvent('End(AdminNavigatesToProductsPage)')],
    [Ctrl.markEvent('End(AdminNavigatesToProductsPage)'), Ctrl.markEvent('End(AdminFiltersProduct)')],
    [Ctrl.markEvent('End(AdminFiltersProduct)'), Ctrl.markEvent('End(AdminEditsProductsPrice)')]
    ];

/**
 * Ranks test suites by how many events from the GOALS array were met.
 * The more goals are met, the higher the score.
 * 
 * It make no difference if a goal was met more then once.
 *
 * @param {Event[][]} ensemble The test suite to be ranked.
 * @returns Number of events from GOALS that have been met.
 */
function rankByMetGoals( ensemble ) {
    const unreachedGoals = [];
    for ( let idx=0; idx<GOALS.length; idx++ ) {
        unreachedGoals.push(GOALS[idx]);
    }

    for (let testIdx = 0; testIdx < ensemble.length; testIdx++) {
        let test = ensemble[testIdx];
        for (let eventIdx = 0; eventIdx < test.length; eventIdx++) {
            let event = test[eventIdx];
            for (let ugIdx=unreachedGoals.length-1; ugIdx >=0; ugIdx--) {
                let unreachedGoal = unreachedGoals[ugIdx];
                if ( unreachedGoal.contains(event) ) {
                    unreachedGoals.splice(ugIdx,1);
                }
            }
        }
    }

    return GOALS.length-unreachedGoals.length;
}

/**
rank goals by two way coverage
 same like last function, but tests for pairs of goals
 */
function rankByMetGoalsTwoWay(ensemble) {
    const unreachedGoals = [];
    for ( let idx=0; idx<GOALS_2WAY.length; idx++ ) {
        unreachedGoals.push(GOALS_2WAY[idx]);
    }

    for (let testIdx = 0; testIdx < ensemble.length; testIdx++) {
        let test = ensemble[testIdx];
        for (let eventIdx = 0; eventIdx < test.length -1; eventIdx++) {
            for (let eventIdx2 = eventIdx+1; eventIdx2 < test.length; eventIdx2++) {
                let event = test[eventIdx];
                let nextEvent = test[eventIdx2];
                for (let ugIdx=unreachedGoals.length-1; ugIdx >=0; ugIdx--) {
                    let unreachedGoal = unreachedGoals[ugIdx];
                    if ( unreachedGoal[0].contains(event) && unreachedGoal[1].contains(nextEvent)) {
                        unreachedGoals.splice(ugIdx,1);
                    }
                }
            }
        }
    }

    return GOALS_2WAY.length - unreachedGoals.length;
}

/**
 * Ranks potential test suites based on the percentage of goals they cover.
 * Goal events are defined in the GOALS array above. An ensemble with rank
 * 100 covers all the goal events.
 *
 * Multiple ranking functions are supported - to change ranking function,
 * use the `ensemble.ranking-function` configuration key, or the 
 * --ranking-function <functionName> command-line parameter.
 *
 * @param {Event[][]} ensemble the test suite/ensemble to be ranked
 * @returns the percentage of goals covered by `ensemble`.
 */
 function rankingFunction(ensemble) {

    // How many goals did `ensemble` hit?
    //const metGoalsCount = rankByMetGoals(ensemble);
    const metGoalsCount = rankByMetGoalsTwoWay(ensemble);
    // What percentage of the goals did `ensemble` cover?
    //const metGoalsPercent = metGoalsCount/GOALS.length;
    const metGoalsPercent = metGoalsCount/(GOALS_2WAY.length);

    return metGoalsPercent * 100; // convert to human-readable percentage
}

