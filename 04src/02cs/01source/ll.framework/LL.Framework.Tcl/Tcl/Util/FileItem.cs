//-----------------------------------------------------------------------
// <copyright file="FileItem.cs">
// </copyright>
// <author>liul</author>
// <date>2015年11月25号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Tcl.Tcl.Util
{
    using System;
    using System.IO;

    /// <summary>
    /// 文件元数据。
    /// 可以使用以下几种构造方法：
    /// 本地路径：new FileItem("C:/temp.jpg");
    /// 本地文件：new FileItem(new FileInfo("C:/temp.jpg"));
    /// 字节流：new FileItem("abc.jpg", bytes);
    /// </summary>
    public class FileItem
    {
        /// <summary>
        /// 文件名称.
        /// </summary>
        private string fileName;
        /// <summary>
        /// 文件类型.
        /// </summary>
        private string mimeType;
        /// <summary>
        /// 文件字节流.
        /// </summary>
        private byte[] content;
        /// <summary>
        /// 文件类.
        /// </summary>
        private FileInfo fileInfo;

        /// <summary>
        /// 基于本地文件的构造器。
        /// </summary>
        /// <param name="fileInfo">本地文件</param>
        public FileItem(FileInfo fileInfo)
        {
            if (fileInfo == null || !fileInfo.Exists)
            {
                throw new ArgumentException("fileInfo is null or not exists!");
            }
            this.fileInfo = fileInfo;
        }

        /// <summary>
        /// 基于本地文件全路径的构造器。
        /// </summary>
        /// <param name="filePath">本地文件全路径</param>
        public FileItem(string filePath)
            : this(new FileInfo(filePath))
        { }

        /// <summary>
        /// 基于文件名和字节流的构造器。
        /// </summary>
        /// <param name="fileName">文件名称（服务端持久化字节流到磁盘时的文件名）</param>
        /// <param name="content">文件字节流</param>
        public FileItem(string fileName, byte[] content)
        {
            if (string.IsNullOrEmpty(fileName)) throw new ArgumentNullException("fileName");
            if (content == null || content.Length == 0) throw new ArgumentNullException("content");

            this.fileName = fileName;
            this.content = content;
        }

        /// <summary>
        /// 基于文件名、字节流和媒体类型的构造器。
        /// </summary>
        /// <param name="fileName">文件名（服务端持久化字节流到磁盘时的文件名）</param>
        /// <param name="content">文件字节流</param>
        /// <param name="mimeType">媒体类型</param>
        public FileItem(string fileName, byte[] content, string mimeType)
            : this(fileName, content)
        {
            if (string.IsNullOrEmpty(mimeType)) throw new ArgumentNullException("mimeType");
            this.mimeType = mimeType;
        }

        /// <summary>
        /// 获取文件名称(完整路径).
        /// </summary>
        /// <returns>文件名(如C:\temp\text.txt).</returns>
        public string GetFileName()
        {
            if (this.fileName == null && this.fileInfo != null && this.fileInfo.Exists)
            {
                this.fileName = this.fileInfo.FullName;
            }
            return this.fileName;
        }

        /// <summary>
        /// 获取文件类型.
        /// </summary>
        /// <returns>文件类型.</returns>
        public string GetMimeType()
        {
            if (this.mimeType == null)
            {
                this.mimeType = TclUtils.GetMimeType(GetContent());
            }
            return this.mimeType;
        }

        /// <summary>
        /// 获取文本流.
        /// </summary>
        /// <returns>文本流.</returns>
        public byte[] GetContent()
        {
            if (this.content == null && this.fileInfo != null && this.fileInfo.Exists)
            {
                using (System.IO.Stream fileStream = this.fileInfo.OpenRead())
                {
                    this.content = new byte[fileStream.Length];
                    fileStream.Read(content, 0, content.Length);
                }
            }

            return this.content;
        }
    }
}
