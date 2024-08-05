package com.kimikevin.nomad_backend.invoice;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {
    InvoiceRepository repository;

    public InvoiceService(InvoiceRepository repository) {
        this.repository = repository;
    }

    public List<Invoice> getAllInvoices() {
        return repository.findAll();
    }

    public Invoice getInvoiceById(Long id) {
//        id = repository.getNextSequenceValue();
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invoice not found"));
    }

    public Invoice createInvoice(Invoice invoice) {
        return repository.save(invoice);
    }

    public Invoice updateInvoice(Long id, Invoice invoiceDetails) {
        Invoice invoice = getInvoiceById(id);

        invoice.setDatePaid(invoiceDetails.getDatePaid());
        invoice.setDateRaised(invoiceDetails.getDateRaised());
        invoice.setCreditCardNo(invoiceDetails.getCreditCardNo());
        invoice.setPMethodNo(invoiceDetails.getPMethodNo());
        invoice.setRegistrationNo(invoiceDetails.getRegistrationNo());
        invoice.setHoldersName(invoiceDetails.getHoldersName());
        invoice.setExpiryDate(invoiceDetails.getExpiryDate());
        return repository.save(invoice);
    }

    public void deleteInvoice(Long id) {
//        id = repository.getNextSequenceValue();
        repository.deleteById(id);
    }
}
