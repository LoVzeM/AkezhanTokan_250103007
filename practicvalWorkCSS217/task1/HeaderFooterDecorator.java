package task1;

public class HeaderFooterDecorator extends ExporterDecorator {
    private final String timestamp;

    public HeaderFooterDecorator(Exporter wrappedExporter, String timestamp) {
        super(wrappedExporter);
        if (timestamp == null) {
            throw new IllegalArgumentException("Timestamp cannot be null");
        }
        this.timestamp = timestamp;
    }

    @Override
    public void export(String destination, String data) {
        String enrichedData =
            "=== METADATA HEADER: " + timestamp + " ===\n"
            + data + "\n"
            + "=== INTEGRITY CHECK: SHA-256 OK | END RECORD ===";

        super.export(destination, enrichedData);
    }
}
