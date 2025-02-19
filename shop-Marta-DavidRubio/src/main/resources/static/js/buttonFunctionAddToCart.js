
function addToCart(button) {
    const productId = button.getAttribute("data-product-id")
    const quantity = 1;
    console.log("productId", productId);

    Array.from(document.getElementsByClassName("add-to-cart"))
        .forEach(button => {
            console.log(button);
            button.addEventListener("click", () => {
                const units = 1;
                fetch(appPath+"api/cart", {
                    method: "POST",
                    body: JSON.stringify({
                        //el productoId, y la cantidad del producto
                        productId,
                        quantity
                    }),
                    headers: {
                        "Content-type": "application/json; charset=UTF-8"
                    }
                })
                    .then(value => {
                        //alert ok
                        // fetch(appPath+"api/cart")
                        //     .then(response => {
                        //         response.text().then(text => {
                        //             const productos = JSON.parse(text);
                        //             console.table(productos);
                        //         });
                        //     });
                    })
                    .catch(reason => alert("Error"))
            })
        })
}

