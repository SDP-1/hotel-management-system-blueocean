package view.CenterPanel.tm;

public class IncomeReportAnuwalyTM {
    private int no;
    private String description;
    private String receivableRs;
    private String receivable$;
    private String receivedRs;
    private String received$;

    public IncomeReportAnuwalyTM() {
    }

    public IncomeReportAnuwalyTM(int no, String description, String receivableRs, String receivable$, String receivedRs, String received$) {
        this.no = no;
        this.description = description;
        this.receivableRs = receivableRs;
        this.receivable$ = receivable$;
        this.receivedRs = receivedRs;
        this.received$ = received$;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReceivableRs() {
        return receivableRs;
    }

    public void setReceivableRs(String receivableRs) {
        this.receivableRs = receivableRs;
    }

    public String getReceivable$() {
        return receivable$;
    }

    public void setReceivable$(String receivable$) {
        this.receivable$ = receivable$;
    }

    public String getReceivedRs() {
        return receivedRs;
    }

    public void setReceivedRs(String receivedRs) {
        this.receivedRs = receivedRs;
    }

    public String getReceived$() {
        return received$;
    }

    public void setReceived$(String received$) {
        this.received$ = received$;
    }
}
