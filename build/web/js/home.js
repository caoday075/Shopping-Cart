
var shoppingCart = (function () {

    cart = [];

    function Item(id, name, price, count) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
    }

    // Save cart
    function saveCart() {
        sessionStorage.setItem('shoppingCart', JSON.stringify(cart));
    }

    // Load cart
    function loadCart() {
        cart = JSON.parse(sessionStorage.getItem('shoppingCart'));
    }
    if (sessionStorage.getItem("shoppingCart") != null) {
        loadCart();
    }

    var obj = {};

    obj.addItemToCart = function (id, name, price, count) {

        for (var item in cart) {
            if (cart[item].id === id) {
                if (cart[item].count < 10) {
                    cart[item].count++;
                    saveCart();
                    return;
                } else
                    return;
            }
        }
        var item = new Item(id, name, price, count);
        cart.push(item);
        saveCart();

    }

    obj.setCountForItem = function (id, count) {
        for (var i in cart) {
            if (cart[i].id === id) {
                if (count > 0 && count <= 10) {
                    cart[i].count = count;
                    break;
                } else {
                    break;
                }
            }
        }
    };
    // Remove item from cart
    obj.removeItemFromCart = function (id) {
        for (var item in cart) {
            if (cart[item].id === id) {
                cart[item].count--;
                if (cart[item].count === 0) {
                    cart.splice(item, 1);
                }
                break;
            }
        }
        saveCart();
    }

    // Remove all items from cart
    obj.removeItemFromCartAll = function (id) {
        for (var item in cart) {
            if (cart[item].id === id) {
                cart.splice(item, 1);
                break;
            }
        }
        saveCart();
    }

    // Clear cart
    obj.clearCart = function () {
        cart = [];
        saveCart();
    }

    // Count cart 
    obj.totalCount = function () {
        var totalCount = 0;
        for (var item in cart) {
            totalCount += cart[item].count;
        }
        return totalCount;
    }

    // Total cart
    obj.totalCart = function () {
        var totalCart = 0;
        for (var item in cart) {
            totalCart += cart[item].price * cart[item].count;
        }
        return Number(totalCart.toFixed(2));
    }

    // List cart
    obj.listCart = function () {
        var cartCopy = [];
        for (i in cart) {
            item = cart[i];
            itemCopy = {};
            for (p in item) {
                itemCopy[p] = item[p];

            }
            itemCopy.total = Number(item.price * item.count).toFixed(2);
            cartCopy.push(itemCopy)
        }
        return cartCopy;
    }

    // cart : Array
    // Item : Object/Class
    // addItemToCart : Function
    // removeItemFromCart : Function
    // removeItemFromCartAll : Function
    // clearCart : Function
    // countCart : Function
    // totalCart : Function
    // listCart : Function
    // saveCart : Function
    // loadCart : Function
    return obj;
})();


// *****************************************
// Triggers / Events
// ***************************************** 
// Add item
$('.add-to-cart').click(function (event) {
    event.preventDefault();
    var id = $(this).data('id');
    var name = $(this).data('name');
    var price = Number($(this).data('price'));
    shoppingCart.addItemToCart(id, name, price, 1);
    displayCart();
});

// Clear items
$('.clear-cart').click(function () {
    shoppingCart.clearCart();
    displayCart();
});


function displayCart() {
    var cartArray = shoppingCart.listCart();
    var output = "";
    var header = "<tr class='row'>"
            + "<th class='col-md-3'>Product</th>"
            + "<th class='col-md-2'>Price</th>"
            + "<th class='col-md-3'>Quantity</th>"
            + "<th class='col-md-2'></th>"
            + "<th class='col-md-2'>Total</th>"
            + "</tr>";
    var result;
    for (var i in cartArray) {
        output += "<tr class='row''>"
                // + "<td class='col-md-1'>" + cartArray[i].id + "</td>"
                + "<td class='col-md-3'>" + cartArray[i].name + "</td>"
                + "<td class='col-md-2'>" + cartArray[i].price + "</td>"
                + "<td class='col-md-3'><div class='input-group'><button class='minus-item input-group-addon btn btn-primary' data-id=" + cartArray[i].id + ">-</button>"
                + "<input type='number' class='item-count form-control' data-id='" + cartArray[i].id + "' value='" + cartArray[i].count + "'>"
                + "<button class='plus-item btn btn-primary input-group-addon' data-id=" + cartArray[i].id + ">+</button></div></td>"
                + "<td class='col-md-2'><button class='delete-item btn btn-danger' data-id=" + cartArray[i].id + ">X</button></td>"
                + " = "
                + "<td class='col-md-2'>" + cartArray[i].total + "</td>"
                + "<input type='hidden' name='txtProID' value='" + cartArray[i].id + "'>"
                + "<input type='hidden' name='txtProQuantity' value='" + cartArray[i].count + "'>"

                + "</tr>"

    }
    result = header + output;
    $('.show-cart').html(result);
    $('.total-cart').html(shoppingCart.totalCart());
    $('.total-count').html(shoppingCart.totalCount());

    if (cartArray.length === 0) {
        $('#btnBuy').attr("disabled", true);
    } else {
        $('#btnBuy').removeAttr("disabled")
    }

}

// Delete item button
$('.show-cart').on("click", ".delete-item", function (event) {
    var name = $(this).data('id')
    shoppingCart.removeItemFromCartAll(name);
    displayCart();
})


// -1
$('.show-cart').on("click", ".minus-item", function (event) {
    var name = $(this).data('id')
    shoppingCart.removeItemFromCart(name);
    displayCart();
})
// +1
$('.show-cart').on("click", ".plus-item", function (event) {
    var name = $(this).data('id')
    shoppingCart.addItemToCart(name);
    displayCart();
})

// Item count input
$('.show-cart').on("change", ".item-count", function (event) {
    var name = $(this).data('id');
    var count = Number($(this).val());
    shoppingCart.setCountForItem(name, count);
    displayCart();
});


displayCart();

