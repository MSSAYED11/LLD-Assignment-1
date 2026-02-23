public class TextSanitizingExporter extends Exporter {
    private final Exporter delegate;

    public TextSanitizingExporter(Exporter delegate) {
        this.delegate = delegate;
    }

    @Override
    protected ExportResult doExport(ExportRequest req) {
        String cleanBody = req.body == null ? "" : req.body.replace("\n", " ").replace(",", " ");
        ExportRequest cleanReq = new ExportRequest(req.title, cleanBody);
        return delegate.export(cleanReq);
    }
}