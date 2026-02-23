public class SizeLimitedExporter extends Exporter {
    private final Exporter delegate;
    private final int maxLength;
    private final String formatName;

    public SizeLimitedExporter(Exporter delegate, int maxLength, String formatName) {
        this.delegate = delegate;
        this.maxLength = maxLength;
        this.formatName = formatName;
    }

    @Override
    protected ExportResult doExport(ExportRequest req) {
        if (req.body != null && req.body.length() > maxLength) {
            throw new IllegalArgumentException(formatName + " cannot handle content > " + maxLength + " chars");
        }
        return delegate.export(req);
    }
}