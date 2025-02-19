// const productId = button.getAttribute("data-product-id")
// const quantity = 1;
// console.log("productId", productId);

Array.from(document.getElementsByClassName("add-to-cart"))
    .forEach(button => {
        console.log(button);
        button.addEventListener("click", () => {
            const units = 1;
            fetch(appPath+"api/cart", {
                method: "POST",
                body: JSON.stringify({
                    //el productoId, y la cantidad del producto
                    productId : button.getAttribute("data-product-id"),
                    quantity : 1
                }),
                headers: {
                    "Content-type": "application/json; charset=UTF-8"
                }
            })
                .then(response => {

                    if (response.ok){
                        document.getElementById("toast").style.display= "block";
                        showToast()
                    }else{
                       // document.getElementById("toast").style.display= "none";
                        document.getElementById("toast").style.display= "block";
                        document.getElementById("toast").style.backgroundColor = "red"
                        document.getElementById("toast").textContent ="No se ha añadido. No hay stock"
                        showToast()

                    }

                })
                .catch(reason => alert("Error"))
        })
    })

function showToast() {
    let toast = document.getElementById("toast");
    toast.classList.add("show");

    setTimeout(() => {
        toast.classList.remove("show");
    }, 3000);
}



