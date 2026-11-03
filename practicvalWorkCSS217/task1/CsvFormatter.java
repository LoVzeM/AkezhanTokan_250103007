package task1;

public class CsvFormatter implements ReportFormatter {

    @Override
    public String format(String title, String[] headers, String[][] rows) {
        StringBuilder sb = new StringBuilder();
        sb.append("# TITLE: ").append(csvEscape(title)).append("\n");

        for (int i = 0; i < headers.length; i++) {
            if (i > 0) sb.append(",");
            sb.append(csvEscape(headers[i]));
        }
        sb.append("\n");

        for (String[] row : rows) {
            for (int i = 0; i < headers.length; i++) {
                if (i > 0) sb.append(",");
                sb.append(csvEscape(i < row.length ? row[i] : ""));
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    private String csvEscape(String value) {
        if (value == null) return "\"\"";
        String escaped = value.replace("\"", "\"\"");
        if (escaped.contains(",") || escaped.contains("\"")
                || escaped.contains("\n") || escaped.contains("\r")) {
            return "\"" + escaped + "\"";
        }
        return escaped;
    }
}
