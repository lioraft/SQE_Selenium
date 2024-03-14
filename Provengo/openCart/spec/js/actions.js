// @provengo summon ctrl

function userLogIn(s) {
  sync({request: Event('Start(UserLogIn)')})
  with (s) {
    // login
    click(xpathsUser.HomePage.myAccountButton)
    click(xpathsUser.HomePage.loginButton)
    // enter email and password in inputs
    writeText(xpathsUser.LoginPage.emailInput, userEmail)
    writeText(xpathsUser.LoginPage.passwordInput, userPassword)
    // scroll down
    runCode(scrolling.down)
    // click login button
    click(xpathsUser.LoginPage.loginButton)
  }
  sync({
    request: Event("End(UserLogIn)"),
    request: Ctrl.markEvent('End(UserLogIn)')
  })
}

function userSearchProduct(s) {
  sync({ request: Event("Start(UserSearchProduct)"),
  waitFor: Event("End(UserLogIn)")})
  with(s) {
    // search for product
    writeText(xpathsUser.ProductPage.searchInput, product)
    click(xpathsUser.ProductPage.searchButton)
  }
  sync({ request: Event("End(UserSearchProduct)"),
  request: Ctrl.markEvent('End(UserSearchProduct)')})
}

function userAddToCart(s) {
  sync({ request: Event("Start(UserAddToCart)"),
  block: Event("Start(AdminEditsProductsPrice)"),
  waitFor: Event("End(UserSearchProduct)")})
  with(s) {
    // scroll down
    runCode(scrolling.down)
    // wait for clickables to appear
    waitForClickability(xpathsUser.ProductPage.addToCartButton)
    // add product to cart
    click(xpathsUser.ProductPage.addToCartButton)
    // wait for success message
    waitForVisibility(xpathsUser.ProductPage.addedToCartMessage)
    // close success message
    click(xpathsUser.ProductPage.successMessageCloseButton)
  }
  sync({ request: Event("End(UserAddToCart)"),
  request: Ctrl.markEvent('End(UserAddToCart)')})
}

function userNavigatesToCheckout(s) {
  sync({ request: Event("Start(UserNavigatesToCheckout)"),
  waitFor: Event("End(UserAddToCart)")})
  with (s) {
    // scroll up
    runCode(scrolling.up)
    // wait for items in cart button to appear
    waitForVisibility(xpathsUser.ProductPage.itemsInCartButton)
    // wait for clickable to appear
    waitForClickability(xpathsUser.ProductPage.itemsInCartButton)
    // navigate to checkout
    click(xpathsUser.ProductPage.itemsInCartButton)
    click(xpathsUser.ProductPage.checkoutButton)
  }
  sync({ request: Event("End(UserNavigatesToCheckout)"),
  allow: Event("Start(AdminEditsProductsPrice)"),
  request: Ctrl.markEvent('End(UserNavigatesToCheckout)')})
}
function adminLogsIn(s) {
  sync({ request: Event("Start(AdminLogsIn)")})
  with(s) {
    // login
    writeText(xpathsAdmin.LoginPage.usernameInput, adminUserName)
    writeText(xpathsAdmin.LoginPage.passwordInput, adminPassword)
    click(xpathsAdmin.LoginPage.loginButton)
  }
  sync({ request: Event("End(AdminLogsIn)"),
  request: Ctrl.markEvent('End(AdminLogsIn)')})
}

function adminNavigatesToProductsPage(s) {
  sync({ request: Event("Start(AdminNavigatesToProductsPage)"),
  waitFor: Event("End(AdminLogsIn)")})
  with(s) {
    // // wait for dashboard to load
    waitForVisibility(xpathsAdmin.Dashboard.catalogButton)
    click(xpathsAdmin.Dashboard.catalogButton)
    click(xpathsAdmin.Dashboard.productsButton)
  }
  sync({ request: Event("End(AdminNavigatesToProductsPage)"),
  request: Ctrl.markEvent('End(AdminNavigatesToProductsPage)')})
}

function adminFiltersProduct(s) {
  sync({ request: Event("Start(AdminFiltersProduct)"),
  waitFor: Event("End(AdminNavigatesToProductsPage)")})
  with(s) {
    // wait for input to appear
    waitForVisibility(xpathsAdmin.Dashboard.productNameInput)
    // filter product
    writeText(xpathsAdmin.Dashboard.productNameInput, product)
    // scroll down
    runCode(scrolling.down)
    // wait for filter button to appear
    waitForClickability(xpathsAdmin.Dashboard.filterButton)
    // click filter button
    click(xpathsAdmin.Dashboard.filterButton)
  }
  sync({ request: Event("End(AdminFiltersProduct)"),
  request: Ctrl.markEvent('End(AdminFiltersProduct)')})
}

function adminEditsProductsPrice(s) {
  // when admin edits product price, user should not be able to add it to cart
  sync({ request: Event("Start(AdminEditsProductsPrice)"),
  block: Event("Start(UserAddToCart)"),
  waitFor: Event("End(AdminFiltersProduct)")})
  with(s) {
    // edit product
    click(xpathsAdmin.Dashboard.editProductButton)
    // wait for data tab to appear
    waitForVisibility(xpathsAdmin.Dashboard.dataOfProductButton)
    // navigate to data tab
    click(xpathsAdmin.Dashboard.dataOfProductButton)
    // edit price
    writeText(xpathsAdmin.ProductPage.priceInput, productPrice)
    // scroll up
    runCode(scrolling.up)
    // wait for save button to appear
    waitForClickability(xpathsAdmin.ProductPage.saveButton)
    // save
    click(xpathsAdmin.ProductPage.saveButton)
  }
  sync({ request: Event("End(AdminEditsProductsPrice)"),
  allow: Event("End(UserAddToCart)") ,
  request: Ctrl.markEvent('End(AdminEditsProductsPrice)')})
}

