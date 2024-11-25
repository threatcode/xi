package edu.utils.upload;

import java.awt.image.ColorModel;
import java.awt.image.renderable.ParameterBlock;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.SeekableStream;

import edu.action.common.BaseAction;

public class ImageUploadAction  extends BaseAction{
	
	public String uploadImage(){
		byte[] b = null;
		String imagename = "";
		FileOutputStream fout = null;
		InputStream is = null;
		File file = null;
		int dotPos = 0;
		String extension = "";
		String filetype = "";
		String sessionid = request.getSession().getId();
		try
		{
			/*if(studentForm.getUploadfile()==null)
				return null;
			int filesize = studentForm.getUploadfile().getFileSize();
			b = studentForm.getUploadfile().getFileData();
			filetype = studentForm.getUploadfile().getContentType();*/
			if((b.length/1024) > 76)
			{
				response.getWriter().write("big size");
				return null;
			}
				
			byte[] b1 = b;
			String type = filetype.split("/")[1];
			if(type.endsWith("png"))type = "png";
			else if(type.endsWith("jpeg"))type = "jpeg";
				else if(type.endsWith("jpg"))type = "jpg";
				else if(type.endsWith("gif"))type = "png";
				else if(type.endsWith("bmp"))type = "bmp";
				else				
				{
					response.getWriter().write("format does not match");
					return null;
				}

				String t = "";
				if (type.equalsIgnoreCase("jpeg")|| type.equalsIgnoreCase("jpg"))
					t = "JPEG";
				else if (type.equalsIgnoreCase("png")|| type.equalsIgnoreCase("gif"))
					t = "PNG";
				else if (type.equalsIgnoreCase("bmp"))
					t = "BMP";

				int dimX = 150;
				int dimY = 180;
				try
				{
					is = new ByteArrayInputStream(b1);
					SeekableStream s = SeekableStream.wrapInputStream(is, true);
					RenderedOp objImage = JAI.create("stream", s);
					//((OpImage) objImage.getRendering()).setTileCache(null);

					float width = (float) objImage.getWidth();
					float height = (float) objImage.getHeight();
					
					if(!(Math.abs((((Math.round(width/150.0)*150.0)-width)*100.0)/width)<15.0 && Math.abs((((Math.round(height/180.0)*180.0)-height)*100.0)/height)<15.0))
					{
    					response.getWriter().write("resolution");
    					return null;
    				}
					
					
//                    if (width%150!=0 && height%180!=0)
//    				{
//    					response.getWriter().write("resolution");
//    					return null;
//    				}
                    
                    
//                    BufferedImage bufferedImage = new BufferedImage(objImage.getHeight(), objImage.getWidth(), BufferedImage.TYPE_BYTE_GRAY);
//                    
//                    
//                    ColorModel  imgColor1=bufferedImage.getColorModel();
//                    if(!imgColor1.getColorSpace().isCS_sRGB()) 
//                    	return null;
                    
                    ColorModel  imgColor=objImage.getColorModel();

                    if(!imgColor.getColorSpace().isCS_sRGB()) 
    				{
    					response.getWriter().write("black white");
    					return null;
    				}

                    

					ParameterBlock pb = new ParameterBlock();
					pb.addSource(objImage); // The source image
					pb.add(dimX/width); // The xScale
					pb.add(dimY/height); // The yScale
					pb.add(0.0F); // The x translation
					pb.add(0.0F); // The y translation
					pb.add(new InterpolationNearest()); // The interpolation

					objImage = JAI.create("scale", pb, null);

					response.setHeader("Content-type", "image/jpeg");
    				response.setHeader("Content-type", "image/jpeg");
    				
					ByteArrayOutputStream out = new ByteArrayOutputStream();

					ImageEncoder imgEnc = ImageCodec.createImageEncoder(t, out,
							null);
					try{
					imgEnc.encode(objImage);
					} catch (IOException e) {
				        System.out.println("READ ERROR");
				        e.printStackTrace();
				       // response.getWriter().write("Property Mismatch. Try a new Image");
				    } catch (Exception e) {
				        System.out.println("TAGGER ERROR");
				        //response.getWriter().write("Property Mismatch. Try a new Image");
				        e.getMessage();
				    } catch(java.lang.IncompatibleClassChangeError e){
				    	//response.getWriter().write("Property Mismatch. Try a new Image");
				    	System.out.println("Incompatable");
				    	response.getWriter().write("Incompatable");
				        e.printStackTrace();
				        return null;
				    }
					byte[] finaldata = out.toByteArray();					
					String  e = request.getHeader("user-agent");
					String random = Integer.toString((int)(Math.random()*10000000));
					request.setAttribute("randomfilename",random);
					request.getSession().setAttribute("randomfilename",random);
					if (e.startsWith("Opera"))
					{

						try
						{
//							ServletContext serv = servlet.getServletContext();
//							String realPath = serv.getRealPath("resource/images/userimage/");
//							FileOutputStream fout1 = new FileOutputStream(new File(realPath + "/" + random + ".jpg"));
//							fout1.write(finaldata);
//							fout1.close();
//							response.setHeader("Content-type", "text/html");
//							response.getOutputStream().write(("/UGA/resource/images/userimage/"+ random + ".jpg").getBytes());
						} catch (Exception e1)
						{
							e1.printStackTrace();
						}
					}
					else 
					{
//						response.setHeader("Content-type", "image/jpeg");				
//						response.getOutputStream().write(finaldata);
//						ServletContext serv = servlet.getServletContext();
//						String realPath = serv.getRealPath("resource/images/userimage/");
//						FileOutputStream fout1 = new FileOutputStream(new File(realPath + "/" + random + ".jpg"));
//						fout1.write(finaldata);
//						fout1.close();
					}
					request.getSession().setAttribute("uploadedimage",finaldata);
				} catch (Exception e)
				{
					e.printStackTrace();
					return null;
				}

			}

        catch (Exception e)
		{
			e.printStackTrace();
			try{
			response.setHeader("Content-type", "text/html");
			response.getOutputStream()
					.write(("File Size Exceeds").getBytes());
			//studentForm.setUploadfile(null);
			}catch(Exception e1){}
		}
		return null;
	}

}
