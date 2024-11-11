def calculate_price(baseprice, specialprice, extraprice, extras, discount):
    addon_discount = 0

    if extras >= 3:
        addon_discount = 10
    elif extras >= 5:
        addon_discount = 15
    
    if discount > addon_discount:
        addon_discount = discount

    return baseprice/100 * (100-discount) + specialprice + extraprice/100 * (100-addon_discount)

