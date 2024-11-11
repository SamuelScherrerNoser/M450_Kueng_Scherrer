from main import calculate_price

def test_calculate_price():
    assert calculate_price(100, 50, 200, 3, 10) == 320

if __name__ == "__main__":
    try:
        test_calculate_price()
        print("Test passed")
    except AssertionError:
        print("Test failed")

