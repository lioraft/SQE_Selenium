//@provengo summon selenium

/**
 * User scenario: user logs in, searches for a product, adds it to cart, and navigates to checkout.
 */
bthread('User Scenario', function () {
  // start session
  let s1 = new SeleniumSession(USERSESSION).start(userURL)
  // log in
  userLogIn(s1)
  // search for product
  userSearchProduct(s1)
  // add product to cart
  userAddToCart(s1)
  // navigate to checkout
  userNavigatesToCheckout(s1)
})

/**
 * Admin scenario: admin logs in, navigates to products page, filters product, and edits product price.
 */
bthread('AdminScenario', function () {
  // start session
  let s2 = new SeleniumSession(ADMINSESSION).start(adminURL)
  // log in
  adminLogsIn(s2)
  // navigate to products page
  adminNavigatesToProductsPage(s2)
  // filter product
  adminFiltersProduct(s2)
  // edit product
  adminEditsProductsPrice(s2)
})