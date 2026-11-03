package task1;

public class JsonFormatter implements ReportFormatter {

    @Override
    public String format(String title, String[] headers, String[][] rows) {
        StringBuilder sb = new StringBuilder();

        sb.append("{\n")
          .append("  \"report\": \"").append(escapeJson(title)).append("\",\n")
          .append("  \"records\": [\n");

        for (int i = 0; i < rows.length; i++) {
            sb.append("    {");

            for (int j = 0; j < headers.length; j++) {
                if (j > 0) {
                    sb.append(", ");
                }
                sb.append("\"").append(escapeJson(headers[j])).append("\": \"")
                  .append(escapeJson(j < rows[i].length ? rows[i][j] : ""))
                  .append("\"");
            }

            sb.append("}");
            sb.append(i < rows.length - 1 ? ",\n" : "\n");
        }

        sb.append("  ]\n}");
        return sb.toString();
    }

    private String escapeJson(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t");
    }
}
