package utils;

/**
 * PriceCalculator - utility to parse price strings and compute total order price.
 * Used to compare calculated total vs total shown on Android screen.
 *
 * All prices on Klik Indomaret are in IDR format: "Rp1.000.000"
 */
public class PriceCalculator {

    private PriceCalculator() {}

    /**
     * Parses an IDR price string to a long value (in rupiah).
     * Handles formats: "Rp1.000.000", "Rp 1.000.000", "1.000.000"
     */
    public static long parsePrice(String priceText) {
        if (priceText == null || priceText.isEmpty()) {
            throw new IllegalArgumentException("Price text cannot be null or empty");
        }
        String cleaned = priceText
                .replaceAll("Rp", "")
                .replaceAll("\\.", "")
                .replaceAll(",", "")
                .trim();
        return Long.parseLong(cleaned);
    }

    /**
     * Calculates expected total: productPrice + shippingFee + insuranceFee
     */
    public static long calculateTotal(long productPrice, long shippingFee, long insuranceFee) {
        return productPrice + shippingFee + insuranceFee;
    }

    /**
     * Convenience overload: accepts raw price strings from screen.
     */
    public static long calculateTotal(String productPrice, String shippingFee, String insuranceFee) {
        return calculateTotal(
                parsePrice(productPrice),
                parsePrice(shippingFee),
                parsePrice(insuranceFee)
        );
    }
}
