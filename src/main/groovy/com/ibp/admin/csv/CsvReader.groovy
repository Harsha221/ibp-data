package com.ibp.admin.csv

import com.ibp.admin.AffiliationProductCategory
import com.ibp.admin.VendorUploadData
import com.ibp.admin.commands.AffiliationProductUploadCommand
import com.ibp.admin.commands.AffiliationUploadDataCommand
import com.ibp.admin.commands.CategoryUploadDataCommand
import com.ibp.admin.commands.ProductUploadDataCommand
import com.ibp.admin.commands.SubCategoryUploadDataCommand
import com.opencsv.bean.CsvToBeanBuilder
import groovy.util.logging.Slf4j

@Slf4j
class CsvReader {

    /**
     * Read vendor csv file
     *
     * @param filePath Path of file to be read
     * @return List of {@link VendorUploadData}
     */
    static List<VendorUploadData> readVendorCsv(String filePath) {
        List<VendorUploadData> beans = []
//        try (Reader reader = new FileReader(filePath)) {
        try {
            CsvToBeanBuilder<VendorUploadData> beanBuilder = new CsvToBeanBuilder<>(new InputStreamReader(new FileInputStream(filePath)))
//            beans = new CsvToBeanBuilder(reader).withType(VendorUploadData).withSkipLines(1).build().parse()
            beanBuilder.withType(VendorUploadData.class)
            beans = beanBuilder.build().parse()
        } catch (Exception ex) {
            log.error("@@@readVendorCsv::ERROR::{}", ex)
        }
        beans
    }


    static List<ProductUploadDataCommand> readProductCsv(String filePath) {
        List<ProductUploadDataCommand> beans = []
        try {
            CsvToBeanBuilder<ProductUploadDataCommand> beanBuilder = new CsvToBeanBuilder<>(new InputStreamReader(new FileInputStream(filePath)))
           // beans = new CsvToBeanBuilder(reader).withType(VendorUploadData).withSkipLines(1).build().parse()
            beanBuilder.withType(ProductUploadDataCommand.class)
            beans = beanBuilder.build().parse()
        } catch (Exception ex) {
            log.error("@@@readVendorCsv::ERROR::{}", ex)
        }
        beans
    }

    static List<CategoryUploadDataCommand> readCategoryCsv(String filePath) {
    List<CategoryUploadDataCommand> beans = []
        try {
            CsvToBeanBuilder<CategoryUploadDataCommand> beanBuilder = new CsvToBeanBuilder<>(new InputStreamReader(new FileInputStream(filePath)))
            // beans = new CsvToBeanBuilder(reader).withType(VendorUploadData).withSkipLines(1).build().parse()
            beanBuilder.withType(CategoryUploadDataCommand.class)
            beans = beanBuilder.build().parse()
        } catch (Exception ex) {
            log.error("@@@readCategoryCsv::ERROR::{}", ex)
        }
        beans
    }

    static List<SubCategoryUploadDataCommand> readSubCategoryCsv(String filePath) {
        List<SubCategoryUploadDataCommand> beans = []
        try {
            CsvToBeanBuilder<SubCategoryUploadDataCommand> beanBuilder = new CsvToBeanBuilder<>(new InputStreamReader(new FileInputStream(filePath)))
            // beans = new CsvToBeanBuilder(reader).withType(VendorUploadData).withSkipLines(1).build().parse()
            beanBuilder.withType(SubCategoryUploadDataCommand.class)
            beans = beanBuilder.build().parse()
        } catch (Exception ex) {
            log.error("@@@readCategoryCsv::ERROR::{}", ex)
        }
        beans
    }

    static List<AffiliationProductUploadCommand> readAffiliationProductCsv(String filePath) {
        List<AffiliationProductUploadCommand> beans = []
        try {
            CsvToBeanBuilder<AffiliationProductUploadCommand> beanBuilder = new CsvToBeanBuilder<>(new InputStreamReader(new FileInputStream(filePath)))
            // beans = new CsvToBeanBuilder(reader).withType(VendorUploadData).withSkipLines(1).build().parse()
            beanBuilder.withType(AffiliationProductUploadCommand.class)
            beans = beanBuilder.build().parse()
        } catch (Exception ex) {
            log.error("@@@readCategoryCsv::ERROR::{}", ex)
        }
        beans
    }

    static List<AffiliationUploadDataCommand> readAffiliationCsv(String filePath) {
        List<AffiliationUploadDataCommand> beans = []
        try {
            CsvToBeanBuilder<AffiliationUploadDataCommand> beanBuilder = new CsvToBeanBuilder<>(new InputStreamReader(new FileInputStream(filePath)))
            // beans = new CsvToBeanBuilder(reader).withType(VendorUploadData).withSkipLines(1).build().parse()
            beanBuilder.withType(AffiliationUploadDataCommand.class)
            beans = beanBuilder.build().parse()
        } catch (Exception ex) {
            log.error("@@@readCategoryCsv::ERROR::{}", ex)
        }
        beans
    }
}
