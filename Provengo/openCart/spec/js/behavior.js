/* @provengo summon selenium */

/**
 * This story opens a new browser window, goes to openCart.com, and logs in as a user.
 */
bthread('UserScenario', function () {
  // start session
  let s = new SeleniumSession('user').start(userURL)
  // log in
  userLogIn(s)
  // search for product
  userSearchProduct(s)
  // interrupt if admin changes price
  interrupt(Event("Admin changed the price of the product. Operation interrupted."),function () :void {
    // add product to cart
    userAddToCart(s)
    // navigate to checkout
    userNavigatesToCheckout(s)
  })
})

/**
 * This story opens a new browser window, goes to google.com, and searches for "Pasta" using the "I Feel Lucky" feature.
 */
bthread('AdminScenario', function () {
  // start session
  let s = new SeleniumSession('admin').start(URL)
  // log in
  adminLogsIn(s)
  // navigate to products page
  adminNavigatesToProductsPage(s)
  // filter product
  adminFiltersProduct(s)
  // edit product
  adminEditsProductsPrice(s)
})