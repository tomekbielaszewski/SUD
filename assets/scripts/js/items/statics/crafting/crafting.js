//@ sourceURL=assets/scripts/js/items/statics/crafting/crafting.js
//line above is for IntelliJ debugging purposes

function itemIsOnRecipesList(item) {
    for (i = 0; i < recipes.length; i++) {
        var recipe = recipes[i];
        if (recipe.name === item) {
            return true;
        }
    }
    return false;
}

function getRecipe(item) {
    for (i = 0; i < recipes.length; i++) {
        if (recipes[i].name === item) {
            return recipes[i];
        }
    }
}

function playerHasMaterials(recipe, amountOfItems) {
    var hasAllIngredients = true;

    for (i = 0; i < recipe.ingredients.length; i++) {
        var itemId = recipe.ingredients[i].id;
        var amount = recipe.ingredients[i].amount;

        if (!playerHas(itemId, amount * amountOfItems)) {
            hasAllIngredients = false;
        }
    }
    return hasAllIngredients; //item has no ingredients, so it can be crafted without check
}

function takeMaterialsFromPlayer(recipe, amountOfItems) {
    for (i = 0; i < recipe.ingredients.length; i++) {
        var itemId = recipe.ingredients[i].id;
        var amount = recipe.ingredients[i].amount;
        var itemName = itemRepo.get(itemId).getName();

        equipmentService.removeItems(itemName, amount * amountOfItems, player, response);
    }
}

function giveCraftedItem(recipe, amount) {
    equipmentService.addItems(recipe.name, amount, player, response);
}

function informAboutSuccessfulCrafting(recipe, amount) {
    tellPlayer("Udało Ci się stworzyć " + recipe.name + " w ilości " + amount + "szt. Przedmiot znajdziesz w swoim ekwipunku")
}

function informAboutInsufficientMaterials(recipe, amount) {
    tellPlayer("Masz za mało materiałów!");
}

function informThatItemIsUnknown(item) {
    tellPlayer(item + "? Nie wiem jak stworzyć taki przedmiot...");
}

function craft(item, amount) {
    if (amount <= 0) {
        tellPlayer("Jak chcesz stworzyć taką liczbę przedmiotów?!")
    } else if (!doesPlayerHaveRequiredTools()) {
        informThatPlayerHasNoRequiredTools();
    } else {
        if (itemIsOnRecipesList(item)) {
            var recipe = getRecipe(item);

            if (playerHasMaterials(recipe, amount)) {
                takeMaterialsFromPlayer(recipe, amount);
                giveCraftedItem(recipe, amount);
                informAboutSuccessfulCrafting(recipe, amount);
            } else {
                informAboutInsufficientMaterials(recipe, amount);
            }
        } else {
            informThatItemIsUnknown(item);
        }
    }
}

//######## listing recipes ########

var titleRowPattern = "%1$s\n------------";
var itemDescRowPattern = "[%1$-20s]: \"%2$s\""; //Krótki miecz: Krótkie, lekko uszczerbione, jednoręczne ostrze. Prosty jelec. Zużyta rękojeść. Nic szczególnego.
var ingredientRowPattern = "     %1$-3s %2$s"    //    2x Sztabka żelaza

function printList(title, recipes) {

    tellPlayer(format(titleRowPattern, title));

    for (var i = 0; i < recipes.length; i++) {
        var recipe = recipes[i];
        var item = itemRepo.get(recipe.id);

        tellPlayer(format(itemDescRowPattern, item.getName(), item.getDescription()));

        for (var j = 0; j < recipe.ingredients.length; j++) {
            var ingredient = recipe.ingredients[j];
            var amount = ingredient.amount + "x"
            var item = itemRepo.get(ingredient.id);

            tellPlayer(format(ingredientRowPattern, amount, item.getName()));
        }
    }
}