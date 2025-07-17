import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

// JSON parsing without external library (for simple APIs)
class CurrencyConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Currency Selection
        System.out.println("=== Currency Converter ===");
        System.out.print("Enter base currency (e.g., USD): ");
        String baseCurrency = scanner.next().toUpperCase();

        System.out.print("Enter target currency (e.g., INR): ");
        String targetCurrency = scanner.next().toUpperCase();

        //  Amount Input
        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        // API URL
        String apiUrl = "https://api.exchangerate-api.com/v4/latest/" + baseCurrency;

        try {
            //  Connect to API
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            //  Read response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            reader.close();

            //  Extract exchange rate manually
            String json = jsonBuilder.toString();
            String search = "\"" + targetCurrency + "\":";
            int start = json.indexOf(search) + search.length();
            int end = json.indexOf(",", start);
            if (start == -1 || end == -1) {
                System.out.println("Failed to find exchange rate.");
                return;
            }

            double rate = Double.parseDouble(json.substring(start, end).trim());

            //  Conversion
            double convertedAmount = amount * rate;

            // Display Result
            System.out.printf("Converted Amount: %.2f %s\n", convertedAmount, targetCurrency);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}
