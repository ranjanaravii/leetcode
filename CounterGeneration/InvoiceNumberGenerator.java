package CounterGeneration;


import CounterGeneration.model.Invoice;
import CounterGeneration.model.InvoiceCounter;

import java.time.LocalDate;

public class InvoiceNumberGenerator {

    private final InoviceCounterRepository inoviceCounterRepository;
    private final InvoiceNumberRepository invoiceNumberRepository;

    public InvoiceNumberGenerator(InoviceCounterRepository inoviceCounterRepository, InvoiceNumberRepository invoiceNumberRepository) {
        this.inoviceCounterRepository = inoviceCounterRepository;
        this.invoiceNumberRepository = invoiceNumberRepository;
    }

   // @Transaction
    private String getFinancialYear(LocalDate localDate) {
        int year = localDate.getYear();
        int month = localDate.getMonthValue();

        if (month >= 4) {
            return year + String.valueOf(year + 1).substring(2);
        }
        return year - 1 + String.valueOf(year).substring(2);
    }

    public String generateInvoice() {
        String financialYear = getFinancialYear(LocalDate.now());
        InvoiceCounter counter = null;
        //inoviceCounterRepository.findByFinancialYear(financialYear);

        if (counter != null) {
           counter.setCounter(counter.getCounter() + 1);

        } else {
            counter = new InvoiceCounter();
            counter.setCounter(1L);
            counter.setFinancialYear(financialYear);
        }

       // counter = inoviceCounterRepository.save(counter);

        Invoice invoice = new Invoice();
        String invoiceStr = "INV" + "-" + counter.getFinancialYear() + counter.getCounter();
        invoice.setInvoice(invoiceStr);
       // invoiceNumberRepository.save(invoice);
        return invoiceStr;
    }
}
