package com.bongmai.tiha.utils.sunmiprinter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.RemoteException;
import android.widget.Toast;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.BMConfigInfo;
import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.VattuxuatInfo;
import com.bongmai.tiha.data.prefs.BaseService;
import com.bongmai.tiha.utils.AppUtils;
import com.sunmi.peripheral.printer.ExceptionConst;
import com.sunmi.peripheral.printer.InnerLcdCallback;
import com.sunmi.peripheral.printer.InnerPrinterCallback;
import com.sunmi.peripheral.printer.InnerPrinterException;
import com.sunmi.peripheral.printer.InnerPrinterManager;
import com.sunmi.peripheral.printer.InnerResultCallbcak;
import com.sunmi.peripheral.printer.SunmiPrinterService;
import com.sunmi.peripheral.printer.WoyouConsts;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


/**
 * <pre>
 *      This class is used to demonstrate various printing effects
 *      Developers need to repackage themselves, for details please refer to
 *      http://sunmi-ota.oss-cn-hangzhou.aliyuncs.com/DOC/resource/re_cn/Sunmiprinter%E5%BC%80%E5%8F%91%E8%80%85%E6%96%87%E6%A1%A31.1.191128.pdf
 *  </pre>
 *
 * @author kaltin
 * @since create at 2020-02-14
 */
public class SunmiPrintHelper {

    public static int NoSunmiPrinter = 0x00000000;
    public static int CheckSunmiPrinter = 0x00000001;
    public static int FoundSunmiPrinter = 0x00000002;
    public static int LostSunmiPrinter = 0x00000003;
    Context mContext;
    BaseService BService;
    BMConfigInfo bmConfigInfo;
    /**
     * sunmiPrinter means checking the printer connection status
     */
    public int sunmiPrinter = CheckSunmiPrinter;
    /**
     * SunmiPrinterService for API
     */
    private SunmiPrinterService sunmiPrinterService;

    private static SunmiPrintHelper helper = new SunmiPrintHelper();

    private SunmiPrintHelper() {
    }

    public static SunmiPrintHelper getInstance() {
        return helper;
    }

    private InnerPrinterCallback innerPrinterCallback = new InnerPrinterCallback() {
        @Override
        protected void onConnected(SunmiPrinterService service) {
            sunmiPrinterService = service;
            checkSunmiPrinterService(service);
        }

        @Override
        protected void onDisconnected() {
            sunmiPrinterService = null;
            sunmiPrinter = LostSunmiPrinter;
        }
    };

    /**
     * init sunmi print service
     */
    public void initSunmiPrinterService(Context context) {
        this.mContext = context;
        try {
            boolean ret = InnerPrinterManager.getInstance().bindService(context,
                    innerPrinterCallback);
            if (!ret) {
                sunmiPrinter = NoSunmiPrinter;
            }
        } catch (InnerPrinterException e) {
            e.printStackTrace();
        }
    }

    /**
     * deInit sunmi print service
     */
    public void deInitSunmiPrinterService(Context context) {
        try {
            if (sunmiPrinterService != null) {
                InnerPrinterManager.getInstance().unBindService(context, innerPrinterCallback);
                sunmiPrinterService = null;
                sunmiPrinter = LostSunmiPrinter;
            }
        } catch (InnerPrinterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check the printer connection,
     * like some devices do not have a printer but need to be connected to the cash drawer through a print service
     */
    private void checkSunmiPrinterService(SunmiPrinterService service) {
        boolean ret = false;
        try {
            ret = InnerPrinterManager.getInstance().hasPrinter(service);
        } catch (InnerPrinterException e) {
            e.printStackTrace();
        }
        sunmiPrinter = ret ? FoundSunmiPrinter : NoSunmiPrinter;
    }

    /**
     * Some conditions can cause interface calls to fail
     * For example: the version is too low???device does not support
     * You can see {@link ExceptionConst}
     * So you have to handle these exceptions
     */
    private void handleRemoteException(RemoteException e) {
        //TODO process when get one exception
    }

    /**
     * send esc cmd
     */
    public void sendRawData(byte[] data) {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }
        try {
            sunmiPrinterService.sendRAWData(data, null);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    /**
     * Printer cuts paper and throws exception on machines without a cutter
     */
    public void cutpaper() {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }
        try {
            sunmiPrinterService.cutPaper(null);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    /**
     * Initialize the printer
     * All style settings will be restored to default
     */
    public void initPrinter() {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }
        try {
            sunmiPrinterService.printerInit(null);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    /**
     * paper feed three lines
     * Not disabled when line spacing is set to 0
     */
    public void print3Line() {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }

        try {
            sunmiPrinterService.lineWrap(3, null);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    /**
     * Get printer serial number
     */
    public String getPrinterSerialNo() {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return "";
        }
        try {
            return sunmiPrinterService.getPrinterSerialNo();
        } catch (RemoteException e) {
            handleRemoteException(e);
            return "";
        }
    }

    /**
     * Get device model
     */
    public String getDeviceModel() {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return "";
        }
        try {
            return sunmiPrinterService.getPrinterModal();
        } catch (RemoteException e) {
            handleRemoteException(e);
            return "";
        }
    }

    /**
     * Get firmware version
     */
    public String getPrinterVersion() {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return "";
        }
        try {
            return sunmiPrinterService.getPrinterVersion();
        } catch (RemoteException e) {
            handleRemoteException(e);
            return "";
        }
    }

    /**
     * Get paper specifications
     */
    public String getPrinterPaper() {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return "";
        }
        try {
            return sunmiPrinterService.getPrinterPaper() == 1 ? "58mm" : "80mm";
        } catch (RemoteException e) {
            handleRemoteException(e);
            return "";
        }
    }

    /**
     * Get paper specifications
     */
    public void getPrinterHead(InnerResultCallbcak callbcak) {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }
        try {
            sunmiPrinterService.getPrinterFactory(callbcak);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    /**
     * Get printing distance since boot
     * Some devices need to fetch asynchronously
     */
    public String getPrinterDistance(InnerResultCallbcak callback) {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return "";
        }
        try {
            return sunmiPrinterService.getPrintedLength(callback) + "mm";
        } catch (RemoteException e) {
            handleRemoteException(e);
            return "";
        }
    }

    /**
     * Set printer alignment
     */
    public void setAlign(int align) {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }
        try {
            sunmiPrinterService.setAlignment(align, null);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    /**
     * Due to the distance between the paper hatch and the print head,
     * the paper needs to be fed out automatically
     * But if the Api does not support it, it will be replaced by printing three lines
     */
    public void feedPaper() {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }

        try {
            sunmiPrinterService.autoOutPaper(null);
        } catch (RemoteException e) {
            print3Line();
        }
    }

    /**
     * print text
     * setPrinterStyle Api require V4.2.22 or later, So use esc cmd instead when not supported
     * More settings reference documentation {@link WoyouConsts}
     */
    public void printText(String content, float size, boolean isBold, boolean isUnderLine) {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }

        try {
            try {
                sunmiPrinterService.setPrinterStyle(WoyouConsts.ENABLE_BOLD, isBold ?
                        WoyouConsts.ENABLE : WoyouConsts.DISABLE);
            } catch (RemoteException e) {
                if (isBold) {
                    sunmiPrinterService.sendRAWData(ESCUtil.boldOn(), null);
                } else {
                    sunmiPrinterService.sendRAWData(ESCUtil.boldOff(), null);
                }
            }
            try {
                sunmiPrinterService.setPrinterStyle(WoyouConsts.ENABLE_UNDERLINE, isUnderLine ?
                        WoyouConsts.ENABLE : WoyouConsts.DISABLE);
            } catch (RemoteException e) {
                if (isUnderLine) {
                    sunmiPrinterService.sendRAWData(ESCUtil.underlineWithOneDotWidthOn(), null);
                } else {
                    sunmiPrinterService.sendRAWData(ESCUtil.underlineOff(), null);
                }
            }
            sunmiPrinterService.printTextWithFont(content, null, size, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    /**
     * print Bar Code
     */
    public void printBarCode(String data, int symbology, int height, int width, int textposition) {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }

        try {
            sunmiPrinterService.printBarCode(data, symbology, height, width, textposition, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * print Qr Code
     */
    public void printQr(String data, int modulesize, int errorlevel) {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }

        try {
            sunmiPrinterService.printQRCode(data, modulesize, errorlevel, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print a row of a table
     */
    public void printTable(String[] txts, int[] width, int[] align) {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }

        try {
            sunmiPrinterService.printColumnsString(txts, width, align, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print pictures and text in the specified orde
     * After the picture is printed,
     * the line feed output needs to be called,
     * otherwise it will be saved in the cache
     * In this example, the image will be printed because the print text content is added
     */
    public void printBitmap(Bitmap bitmap, int orientation) {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }

        try {
            if (orientation == 0) {
                sunmiPrinterService.printBitmap(bitmap, null);
                sunmiPrinterService.printText("????????????\n", null);
                sunmiPrinterService.printBitmap(bitmap, null);
                sunmiPrinterService.printText("????????????\n", null);
            } else {
                sunmiPrinterService.printBitmap(bitmap, null);
                sunmiPrinterService.printText("\n????????????\n", null);
                sunmiPrinterService.printBitmap(bitmap, null);
                sunmiPrinterService.printText("\n????????????\n", null);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets whether the current printer is in black mark mode
     */
    public boolean isBlackLabelMode() {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return false;
        }
        try {
            return sunmiPrinterService.getPrinterMode() == 1;
        } catch (RemoteException e) {
            return false;
        }
    }

    /**
     * Transaction printing:
     * enter->print->exit(get result) or
     * enter->first print->commit(get result)->twice print->commit(get result)->exit(don't care
     * result)
     */
    public void printTrans(Context context, InnerResultCallbcak callbcak) {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }

        try {
            sunmiPrinterService.enterPrinterBuffer(true);
            printExample(context);
            sunmiPrinterService.exitPrinterBufferWithCallback(true, callbcak);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Open cash box
     * This method can be used on Sunmi devices with a cash drawer interface
     * If there is no cash box (such as V1???P1) or the call fails, an exception will be thrown
     * <p>
     * Reference to https://docs.sunmi.com/general-function-modules/external-device-debug/cash-box-driver/}
     */
    public void openCashBox() {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }

        try {
            sunmiPrinterService.openDrawer(null);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    /**
     * LCD screen control
     *
     * @param flag 1 ?????? Initialization
     *             2 ?????? Light up screen
     *             3 ?????? Extinguish screen
     *             4 ?????? Clear screen contents
     */
    public void controlLcd(int flag) {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }

        try {
            sunmiPrinterService.sendLCDCommand(flag);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    /**
     * Display text SUNMI,font size is 16 and format is fill
     * sendLCDFillString(txt, size, fill, callback)
     * Since the screen pixel height is 40, the font should not exceed 40
     */
    public void sendTextToLcd() {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }

        try {
            sunmiPrinterService.sendLCDFillString("SUNMI", 16, true, new InnerLcdCallback() {
                @Override
                public void onRunResult(boolean show) throws RemoteException {
                    //TODO handle result
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    /**
     * Display two lines and one empty line in the middle
     */
    public void sendTextsToLcd() {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }

        try {
            String[] texts = {"SUNMI", null, "SUNMI"};
            int[] align = {2, 1, 2};
            sunmiPrinterService.sendLCDMultiString(texts, align, new InnerLcdCallback() {
                @Override
                public void onRunResult(boolean show) throws RemoteException {
                    //TODO handle result
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    /**
     * Display one 128x40 pixels and opaque picture
     */
    public void sendPicToLcd(Bitmap pic) {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }

        try {
            sunmiPrinterService.sendLCDBitmap(pic, new InnerLcdCallback() {
                @Override
                public void onRunResult(boolean show) throws RemoteException {
                    //TODO handle result
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    /**
     * Sample print receipt
     */
    public void printExample(Context context) {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }

        try {
            int paper = sunmiPrinterService.getPrinterPaper();
            sunmiPrinterService.printerInit(null);
            sunmiPrinterService.setAlignment(1, null);
            sunmiPrinterService.printText("????????????\n", null);
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
            sunmiPrinterService.printBitmap(bitmap, null);
            sunmiPrinterService.lineWrap(1, null);
            sunmiPrinterService.setAlignment(0, null);
            try {
                sunmiPrinterService.setPrinterStyle(WoyouConsts.SET_LINE_SPACING, 0);
            } catch (RemoteException e) {
                sunmiPrinterService.sendRAWData(new byte[]{0x1B, 0x33, 0x00}, null);
            }
            sunmiPrinterService.printTextWithFont("???????????????????????????????????????????????????,?????????????????????????????????????????????\n",
                    null, 12, null);
            if (paper == 1) {
                sunmiPrinterService.printText("--------------------------------\n", null);
            } else {
                sunmiPrinterService.printText("------------------------------------------------\n",
                        null);
            }
            try {
                sunmiPrinterService.setPrinterStyle(WoyouConsts.ENABLE_BOLD, WoyouConsts.ENABLE);
            } catch (RemoteException e) {
                sunmiPrinterService.sendRAWData(ESCUtil.boldOn(), null);
            }
            String txts[] = new String[]{"??????", "??????"};
            int width[] = new int[]{1, 1};
            int align[] = new int[]{0, 2};
            sunmiPrinterService.printColumnsString(txts, width, align, null);
            try {
                sunmiPrinterService.setPrinterStyle(WoyouConsts.ENABLE_BOLD, WoyouConsts.DISABLE);
            } catch (RemoteException e) {
                sunmiPrinterService.sendRAWData(ESCUtil.boldOff(), null);
            }
            if (paper == 1) {
                sunmiPrinterService.printText("--------------------------------\n", null);
            } else {
                sunmiPrinterService.printText("------------------------------------------------\n",
                        null);
            }
            txts[0] = "??????";
            txts[1] = "17??";
            sunmiPrinterService.printColumnsString(txts, width, align, null);
            txts[0] = "??????";
            txts[1] = "10??";
            sunmiPrinterService.printColumnsString(txts, width, align, null);
            txts[0] = "??????";
            txts[1] = "11??";
            sunmiPrinterService.printColumnsString(txts, width, align, null);
            txts[0] = "??????";
            txts[1] = "11??";
            sunmiPrinterService.printColumnsString(txts, width, align, null);
            txts[0] = "??????";
            txts[1] = "10??";
            sunmiPrinterService.printColumnsString(txts, width, align, null);
            if (paper == 1) {
                sunmiPrinterService.printText("--------------------------------\n", null);
            } else {
                sunmiPrinterService.printText("------------------------------------------------\n",
                        null);
            }
            sunmiPrinterService.printTextWithFont("??????:          59??\b", null, 40, null);
            sunmiPrinterService.setAlignment(1, null);
            sunmiPrinterService.printQRCode("????????????", 10, 0, null);
            sunmiPrinterService.setFontSize(36, null);
            sunmiPrinterService.printText("????????????", null);
            sunmiPrinterService.autoOutPaper(null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    /**
     * Used to report the real-time query status of the printer, which can be used before each
     * printing
     */
    public void showPrinterStatus(Context context) {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }
        String result = "Interface is too low to implement interface";
        try {
            int res = sunmiPrinterService.updatePrinterState();
            switch (res) {
                case 1:
                    result = "printer is running";
                    break;
                case 2:
                    result = "printer found but still initializing";
                    break;
                case 3:
                    result = "printer hardware interface is abnormal and needs to be reprinted";
                    break;
                case 4:
                    result = "printer is out of paper";
                    break;
                case 5:
                    result = "printer is overheating";
                    break;
                case 6:
                    result = "printer's cover is not closed";
                    break;
                case 7:
                    result = "printer's cutter is abnormal";
                    break;
                case 8:
                    result = "printer's cutter is normal";
                    break;
                case 9:
                    result = "not found black mark paper";
                    break;
                case 505:
                    result = "printer does not exist";
                    break;
                default:
                    break;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }

    int fontHeadline = 30;
    int fontTitle = 25;
    int fontSubtitle = 15;
    int fontDefault = 0;
//    new String[]{"UPC-A", "UPC-E", "EAN13", "EAN8", "CODE39", "ITF", "CODABAR", "CODE93", "CODE128A", "CODE128B", "CODE128C"};
//new String[]{"text null", "up barcode", "down barcode", "up and down barcode"}

    public void printPhieuXuatPOS(Context context, String reportName, PhieuXuatInfo phieuXuatInfo, List<VattuxuatInfo> listPhieuXuatChiTiet) {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }
        BService = new BaseService(mContext);
        bmConfigInfo = BService.GetConfig();
        switch (reportName) {
            case "PhieuXuatDefaultReport":
                PhieuXuatDefaultReport(phieuXuatInfo, listPhieuXuatChiTiet);
                break;
            default:
                break;
        }
    }

    private void PhieuXuatDefaultReport(PhieuXuatInfo phieuXuatInfo, List<VattuxuatInfo> listPhieuXuatChiTiet) {
       // try {
//            int paper = sunmiPrinterService.getPrinterPaper();
//            sunmiPrinterService.printerInit(null);
//            String company = bmConfigInfo.getCompany() + "\n";
//            String address = bmConfigInfo.getAddress() + "\n";
//            String phone = bmConfigInfo.getTel() + "\n";
//            String title = "PHI???U THANH TO??N\n";
//            String soChungTu = "S??? CT: " + phieuXuatInfo.getSoCt() + "\n";
//            String ngay = "Ng??y: " + AppUtils.formatDateToString(phieuXuatInfo.getNgay(), "dd/MM/yyyy") + "\n";
//            String nhanVien = "Nh??n vi??n: " + phieuXuatInfo.getDDBH() + "\n";
//            String kho = "Kho: " + phieuXuatInfo.getMSK() + "\n";
//            String luuY = "L??u ??: " + bmConfigInfo.getLuuY();
//
//            sunmiPrinterService.setAlignment(1, null);
//            //Company
//            sunmiPrinterService.printTextWithFont(company, null, fontHeadline, null);
//            //Address
//            sunmiPrinterService.printTextWithFont(address, null, fontTitle, null);
//            //Phone
//            sunmiPrinterService.printTextWithFont(phone, null, fontTitle, null);
//
//            if (paper == 1) {
//                sunmiPrinterService.printText("--------------------------------\n", null);
//            } else {
//                sunmiPrinterService.printText("------------------------------------------------\n",
//                        null);
//            }
//            //Title
//            try {
//                sunmiPrinterService.setPrinterStyle(WoyouConsts.ENABLE_BOLD, WoyouConsts.ENABLE);
//            } catch (RemoteException e) {
//                sunmiPrinterService.sendRAWData(ESCUtil.boldOn(), null);
//            }
//            sunmiPrinterService.printTextWithFont(title, null, fontHeadline, null);
//            try {
//                sunmiPrinterService.setPrinterStyle(WoyouConsts.ENABLE_BOLD, WoyouConsts.DISABLE);
//            } catch (RemoteException e) {
//                sunmiPrinterService.sendRAWData(ESCUtil.boldOff(), null);
//            }
//            sunmiPrinterService.lineWrap(1, null);
//
//            //Thong tin phieu
//            sunmiPrinterService.setAlignment(0, null);
//            //So chung tu
//            sunmiPrinterService.printTextWithFont(soChungTu, null, fontTitle, null);
//            //Ngay
//            sunmiPrinterService.printTextWithFont(ngay, null, fontTitle, null);
//            //Nhan vien
//            sunmiPrinterService.printTextWithFont(nhanVien, null, fontTitle, null);
//            //Kho
//            sunmiPrinterService.printTextWithFont(kho, null, fontTitle, null);
//            if (paper == 1) {
//                sunmiPrinterService.printText("--------------------------------\n", null);
//            } else {
//                sunmiPrinterService.printText("------------------------------------------------\n",
//                        null);
//            }
//
//            //Detail
//            String txts[] = new String[]{"SL", "G.B??n", "T.Ti???n"};
//            int width[] = new int[]{1, 1, 1};
//            int align[] = new int[]{1, 1, 1};
//            sunmiPrinterService.printColumnsString(txts, width, align, null);
//
//            VattuxuatInfo vattuxuatInfo;
//            double tongTien = 0;
//            for (int i = 0; i < listPhieuXuatChiTiet.size(); i++) {
//                vattuxuatInfo = listPhieuXuatChiTiet.get(i);
//                if (vattuxuatInfo == null) continue;
//                sunmiPrinterService.printTextWithFont(vattuxuatInfo.getProduct_Name(), null, fontTitle, null);
//                sunmiPrinterService.lineWrap(1, null);
//                String txtDetail[] = new String[]{
//                        AppUtils.formatNumber("N0").format(vattuxuatInfo.getSL()),
//                        AppUtils.formatNumber("N0").format(vattuxuatInfo.getDongia()),
//                        AppUtils.formatNumber("N0").format(vattuxuatInfo.getThanh_Tien())};
//                int alignDetail[] = new int[]{1, 1, 2};
//                sunmiPrinterService.printColumnsString(txtDetail, width, alignDetail, null);
//                tongTien += vattuxuatInfo.getThanh_Tien();
//            }
//
//            if (paper == 1) {
//                sunmiPrinterService.printText("--------------------------------\n", null);
//            } else {
//                sunmiPrinterService.printText("------------------------------------------------\n",
//                        null);
//            }
//
//            //Tong tien
//            String txtTongTien[] = new String[]{
//                    "T???ng ti???n:",
//                    AppUtils.formatNumber("N0").format(tongTien)};
//            int widthTongTien[] = new int[]{1, 1};
//            int alignTongTien[] = new int[]{2, 2};
//            try {
//                sunmiPrinterService.setPrinterStyle(WoyouConsts.ENABLE_BOLD, WoyouConsts.ENABLE);
//            } catch (RemoteException e) {
//                sunmiPrinterService.sendRAWData(ESCUtil.boldOn(), null);
//            }
//            sunmiPrinterService.printColumnsString(txtTongTien, widthTongTien, alignTongTien, null);
//            try {
//                sunmiPrinterService.setPrinterStyle(WoyouConsts.ENABLE_BOLD, WoyouConsts.DISABLE);
//            } catch (RemoteException e) {
//                sunmiPrinterService.sendRAWData(ESCUtil.boldOff(), null);
//            }
//            sunmiPrinterService.lineWrap(1, null);
//            //Luu y
//            sunmiPrinterService.printTextWithFont(luuY, null, fontTitle, null);
//            sunmiPrinterService.lineWrap(1, null);
//
//            sunmiPrinterService.setAlignment(1, null);
//            //Barcode
//            try {
//                sunmiPrinterService.printBarCode(phieuXuatInfo.getSoCt()
//                        , 8, 75, 2, 0, null);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//
//            }
//            //Thanks you
//            sunmiPrinterService.lineWrap(1, null);
//            sunmiPrinterService.printTextWithFont("Xin c???m ??n qu?? kh??ch. H???n g???p l???i!!", null, fontTitle, null);
//
//
//            try {
//                sunmiPrinterService.setPrinterStyle(WoyouConsts.SET_LINE_SPACING, 0);
//            } catch (RemoteException e) {
//                sunmiPrinterService.sendRAWData(new byte[]{0x1B, 0x33, 0x00}, null);
//            }
//
//            sunmiPrinterService.autoOutPaper(null);
//
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }

            try {
                int paper = sunmiPrinterService.getPrinterPaper();
                sunmiPrinterService.printerInit(null);
                String company = "QU??N CHAY T??M ?????C" + "\n";
                String address = "S??? 39 ???????ng s??? 3B, Ph?????ng T??n T???o A, Qu???n B??nh T??n, Tp.HCM" + "\n";
                String phone = "??T: 0962400292" + "\n" ;
                String request =  "(Nh???n ?????t ti???c chay)" + "\n";

                String title = "H??A ????N THANH TO??N\n";

                String soChungTu = "S??? h??a ????n: " + phieuXuatInfo.getSoCt().substring(phieuXuatInfo.getSoCt().length() -4) + "\n";
                String ngay = "Ng??y thu: " + AppUtils.formatDateToString(phieuXuatInfo.getNgay(), "dd/MM/yyyy") + "\n";
//                String ngay = "Ng??y thu: " + AppUtils.formatDateToDateRequestSQL(String.valueOf(phieuXuatInfo.getNgay()), "dd/MM/yyyy - HH:mm" ) + "\n";
                String soban = "S??? b??n: 01" +"\n";
                String khachhang = "Kh??ch h??ng: "+ phieuXuatInfo.getTenDDnguoimua() +" - " + phieuXuatInfo.getDiachi() +" - "+phieuXuatInfo.getDTDD()+ "\n";
                String sdt = "S??T: " + phieuXuatInfo.getDTDD()+ "\n";

                String nhanVien = "Thu ng??n: T??m ?????c" + "\n";

                sunmiPrinterService.setAlignment(1, null);
                //Company
                sunmiPrinterService.printTextWithFont(company, null, fontHeadline, null);
                //Address

                sunmiPrinterService.printTextWithFont(address, null, fontTitle, null);
                //Phone
                sunmiPrinterService.printTextWithFont(phone, null, fontTitle, null);

                sunmiPrinterService.printTextWithFont(request, null, fontTitle, null);

                if (paper == 1) {
                    sunmiPrinterService.printText("--------------------------------\n", null);
                } else {
                    sunmiPrinterService.printText("------------------------------------------------\n",
                            null);
                }
                //Title
                try {
                    sunmiPrinterService.setPrinterStyle(WoyouConsts.ENABLE_BOLD, WoyouConsts.ENABLE);
                } catch (RemoteException e) {
                    sunmiPrinterService.sendRAWData(ESCUtil.boldOn(), null);
                }
                sunmiPrinterService.printTextWithFont(title, null, fontHeadline, null);
                try {
                    sunmiPrinterService.setPrinterStyle(WoyouConsts.ENABLE_BOLD, WoyouConsts.DISABLE);
                } catch (RemoteException e) {
                    sunmiPrinterService.sendRAWData(ESCUtil.boldOff(), null);
                }


                //Thong tin phieu
                sunmiPrinterService.setAlignment(0, null);
                //So chung tu
                sunmiPrinterService.printTextWithFont(soChungTu, null, fontTitle, null);
                //Ngay
                sunmiPrinterService.printTextWithFont(ngay, null, fontTitle, null);
                //SoBan
                sunmiPrinterService.printTextWithFont(soban, null, fontTitle, null);
                //Khach hang
                    sunmiPrinterService.printTextWithFont(khachhang, null, fontTitle, null);

                //Nhan vien
                sunmiPrinterService.printTextWithFont(nhanVien, null, fontTitle, null);
                if (paper == 1) {
                    sunmiPrinterService.printText("--------------------------------\n", null);
                } else {
                    sunmiPrinterService.printText("------------------------------------------------\n",
                            null);
                }

                //Detail
                String txts[] = new String[]{"S??? l?????ng", "????n gi??", "Th??nh ti???n"};
                int width[] = new int[]{2, 2, 2};
                int align[] = new int[]{1, 1, 1};
                sunmiPrinterService.printColumnsString(txts, width, align, null);

                VattuxuatInfo vattuxuatInfo = new VattuxuatInfo();
                double tongTien = 0;
                double tongSL = 0;
                String res ="";


                for (int i = 0; i < listPhieuXuatChiTiet.size(); i++) {
                    vattuxuatInfo = listPhieuXuatChiTiet.get(i);
                    if (vattuxuatInfo == null) continue;
                    res = AppUtils.formatNumber("N0").format(i + 1);
                    sunmiPrinterService.printTextWithFont(res +". "+ vattuxuatInfo.getProduct_Name(), null, fontTitle, null);
                    sunmiPrinterService.lineWrap(1, null);
                    String txtDetail[] = new String[]{

                            AppUtils.formatNumber("N0").format(vattuxuatInfo.getSL()),
                            AppUtils.formatNumber("N0").format(vattuxuatInfo.getGiaban()),
                            AppUtils.formatNumber("N0").format(vattuxuatInfo.getThanh_Tien())};
                    int alignDetail[] = new int[]{1, 1, 2};
                    sunmiPrinterService.printColumnsString(txtDetail, width, alignDetail, null);
                    tongTien += vattuxuatInfo.getThanh_Tien();
                    tongSL += vattuxuatInfo.getSL();
                }

                if (paper == 1) {
                    sunmiPrinterService.printText("--------------------------------\n", null);
                } else {
                    sunmiPrinterService.printText("------------------------------------------------\n",
                            null);
                }

                //Tong tien
                String txtTongTien[] = new String[]{
                       AppUtils.formatNumber("N0").format(tongSL), "T???ng ti???n:",
                        AppUtils.formatNumber("N0").format(tongTien)};


                int widthTongTien[] = new int[]{2, 2, 2};
                int alignTongTien[] = new int[]{1, 2, 2};
                try {
                    sunmiPrinterService.setPrinterStyle(WoyouConsts.ENABLE_BOLD, WoyouConsts.ENABLE);
                } catch (RemoteException e) {
                    sunmiPrinterService.sendRAWData(ESCUtil.boldOn(), null);
                }
                sunmiPrinterService.printColumnsString(txtTongTien, widthTongTien, alignTongTien, null);
                try {
                    sunmiPrinterService.setPrinterStyle(WoyouConsts.ENABLE_BOLD, WoyouConsts.DISABLE);
                } catch (RemoteException e) {
                    sunmiPrinterService.sendRAWData(ESCUtil.boldOff(), null);
                }

                sunmiPrinterService.printTextWithFont("\t"+"\t"+ "\t"+"("+ ReadNumber.numberToString(BigDecimal.valueOf(tongTien)) +")", null, fontTitle, null);
                sunmiPrinterService.lineWrap(1, null);

                sunmiPrinterService.setAlignment(1, null);
                //Barcode
                try {
                    sunmiPrinterService.printBarCode(phieuXuatInfo.getSoCt()
                            , 8,    40, 2, 0, null);
                } catch (RemoteException e) {
                    e.printStackTrace();

                }
                //Thanks you
                sunmiPrinterService.lineWrap(1, null);
                sunmiPrinterService.printTextWithFont("C???m ??n qu?? kh??ch. H???n g???p l???i" +"\n", null, fontTitle, null);


                try {
                    sunmiPrinterService.setPrinterStyle(WoyouConsts.SET_LINE_SPACING, 0);
                } catch (RemoteException e) {
                    sunmiPrinterService.sendRAWData(new byte[]{0x1B, 0x33, 0x00}, null);
                }

                sunmiPrinterService.autoOutPaper(null);

            } catch (RemoteException e) {
                e.printStackTrace();
            }

    }


//    private void InComChay(PhieuXuatInfo phieuXuatInfo, List<VattuxuatInfo> listPhieuXuatChiTiet) {
//        try {
//            int paper = sunmiPrinterService.getPrinterPaper();
//            sunmiPrinterService.printerInit(null);
//            String company = "QU??N CHAY T??M ?????C" + "\n";
//            String address = "S??? 39 ???????ng s??? 3B, ph?????ng T??n T???o A, qu???n B??nh T??n, Tp.HCM" + "\n";
//            String phone = "??T: 0962400292" + "\n" + "(Nh???n ?????t ti???c chay)" + "\n";
//
//            String title = "H??A ????N THANH TO??N\n";
//
//            String soChungTu = "S??? h??a ????n: " + phieuXuatInfo.getSoCt() + "\n";
//            String ngay = "Ng??y thu: " + AppUtils.formatDateToString(phieuXuatInfo.getNgay(), "dd/MM/yyyy - HH:mm") + "\n";
//            String khachhang = "Kh??ch h??ng: " + phieuXuatInfo.getTennguoimua();
//            String nhanVien = "Thu ng??n: T??m ?????c" + "\n";
//
//            sunmiPrinterService.setAlignment(1, null);
//            //Company
//            sunmiPrinterService.printTextWithFont(company, null, fontHeadline, null);
//            //Address
//            sunmiPrinterService.printTextWithFont(address, null, fontTitle, null);
//            //Phone
//            sunmiPrinterService.printTextWithFont(phone, null, fontTitle, null);
//
//            if (paper == 1) {
//                sunmiPrinterService.printText("--------------------------------\n", null);
//            } else {
//                sunmiPrinterService.printText("------------------------------------------------\n",
//                        null);
//            }
//            //Title
//            try {
//                sunmiPrinterService.setPrinterStyle(WoyouConsts.ENABLE_BOLD, WoyouConsts.ENABLE);
//            } catch (RemoteException e) {
//                sunmiPrinterService.sendRAWData(ESCUtil.boldOn(), null);
//            }
//            sunmiPrinterService.printTextWithFont(title, null, fontHeadline, null);
//            try {
//                sunmiPrinterService.setPrinterStyle(WoyouConsts.ENABLE_BOLD, WoyouConsts.DISABLE);
//            } catch (RemoteException e) {
//                sunmiPrinterService.sendRAWData(ESCUtil.boldOff(), null);
//            }
//            sunmiPrinterService.lineWrap(1, null);
//
//            //Thong tin phieu
//            sunmiPrinterService.setAlignment(0, null);
//            //So chung tu
//            sunmiPrinterService.printTextWithFont(soChungTu, null, fontTitle, null);
//            //Ngay
//            sunmiPrinterService.printTextWithFont(ngay, null, fontTitle, null);
//            //Khach hang
//            sunmiPrinterService.printTextWithFont(khachhang, null, fontTitle, null);
//            //Nhan vien
//            sunmiPrinterService.printTextWithFont(nhanVien, null, fontTitle, null);
//            if (paper == 1) {
//                sunmiPrinterService.printText("--------------------------------\n", null);
//            } else {
//                sunmiPrinterService.printText("------------------------------------------------\n",
//                        null);
//            }
//
//            //Detail
//            String txts[] = new String[]{"SL", "G.B??n", "T.Ti???n"};
//            int width[] = new int[]{1, 1, 1};
//            int align[] = new int[]{1, 1, 1};
//            sunmiPrinterService.printColumnsString(txts, width, align, null);
//
//            VattuxuatInfo vattuxuatInfo;
//            double tongTien = 0;
//            for (int i = 0; i < listPhieuXuatChiTiet.size(); i++) {
//                vattuxuatInfo = listPhieuXuatChiTiet.get(i);
//                if (vattuxuatInfo == null) continue;
//                sunmiPrinterService.printTextWithFont(vattuxuatInfo.getProduct_Name(), null, fontTitle, null);
//                sunmiPrinterService.lineWrap(1, null);
//                String txtDetail[] = new String[]{
//                        AppUtils.formatNumber("N0").format(vattuxuatInfo.getSL()),
//                        AppUtils.formatNumber("N0").format(vattuxuatInfo.getDongia()),
//                        AppUtils.formatNumber("N0").format(vattuxuatInfo.getThanh_Tien())};
//                int alignDetail[] = new int[]{1, 1, 2};
//                sunmiPrinterService.printColumnsString(txtDetail, width, alignDetail, null);
//                tongTien += vattuxuatInfo.getThanh_Tien();
//            }
//
//            if (paper == 1) {
//                sunmiPrinterService.printText("--------------------------------\n", null);
//            } else {
//                sunmiPrinterService.printText("------------------------------------------------\n",
//                        null);
//            }
//
//            //Tong tien
//            String txtTongTien[] = new String[]{
//                    "T???ng ti???n:",
//                    AppUtils.formatNumber("N0").format(tongTien)};
//            int widthTongTien[] = new int[]{1, 1};
//            int alignTongTien[] = new int[]{2, 2};
//            try {
//                sunmiPrinterService.setPrinterStyle(WoyouConsts.ENABLE_BOLD, WoyouConsts.ENABLE);
//            } catch (RemoteException e) {
//                sunmiPrinterService.sendRAWData(ESCUtil.boldOn(), null);
//            }
//            sunmiPrinterService.printColumnsString(txtTongTien, widthTongTien, alignTongTien, null);
//            try {
//                sunmiPrinterService.setPrinterStyle(WoyouConsts.ENABLE_BOLD, WoyouConsts.DISABLE);
//            } catch (RemoteException e) {
//                sunmiPrinterService.sendRAWData(ESCUtil.boldOff(), null);
//            }
//            sunmiPrinterService.lineWrap(1, null);
//
//            sunmiPrinterService.setAlignment(1, null);
//            //Barcode
//            try {
//                sunmiPrinterService.printBarCode(phieuXuatInfo.getSoCt()
//                        , 8, 75, 2, 0, null);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//
//            }
//            //Thanks you
//            sunmiPrinterService.lineWrap(1, null);
//            sunmiPrinterService.printTextWithFont("Xin c???m ??n qu?? kh??ch. H???n g???p l???i!!", null, fontTitle, null);
//
//
//            try {
//                sunmiPrinterService.setPrinterStyle(WoyouConsts.SET_LINE_SPACING, 0);
//            } catch (RemoteException e) {
//                sunmiPrinterService.sendRAWData(new byte[]{0x1B, 0x33, 0x00}, null);
//            }
//
//            sunmiPrinterService.autoOutPaper(null);
//
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }


}
