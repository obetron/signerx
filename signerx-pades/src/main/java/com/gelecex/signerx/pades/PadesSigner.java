package com.gelecex.signerx.pades;

import com.gelecex.signerx.common.exception.SignerxException;
import com.gelecex.signerx.common.signature.BaseSigner;
import com.gelecex.signerx.pades.dto.PadesSignature;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.ExternalSigningSupport;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by obetron on 6.06.2022
 */
public class PadesSigner implements BaseSigner {

    private PadesSignature padesSignature;

    private PadesSigner(){}

    public PadesSigner(PadesSignature padesSignature) {
        this.padesSignature = padesSignature;
    }

    @Override
    public byte[] createSignature(byte[] dataToBeSigned) throws SignerxException {
        return createPdfSignature(dataToBeSigned);


        //TODO: 1. check the type of byte array is really pdf?
        //TODO: 2. sign byte array pdf in pades format.
    }

    private byte[] createPdfSignature(byte[] originalPdf) throws SignerxException {
        try (PDDocument pdDocument = Loader.loadPDF(originalPdf)){
            PDSignature pdSignature = createPDSignature();
            pdDocument.addSignature(pdSignature);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ExternalSigningSupport exSignSupp = pdDocument.saveIncrementalForExternalSigning(out);

            return new byte[0];
        } catch (IOException e) {
            throw new SignerxException("Error occured during load the pdf", e);
        }
    }

    private PDSignature createPDSignature() {
        PDSignature pdSignature = new PDSignature();
        pdSignature.setFilter(PDSignature.FILTER_ADOBE_PPKLITE);
        pdSignature.setSubFilter(PDSignature.SUBFILTER_ADBE_PKCS7_DETACHED);
        pdSignature.setName(padesSignature.getName());
        pdSignature.setLocation(padesSignature.getLocation());
        pdSignature.setReason(padesSignature.getReason());
        return pdSignature;
    }
}
