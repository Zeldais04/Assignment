USE [Assignment]
GO
/****** Object:  Table [dbo].[Attendence]    Script Date: 10/30/2024 8:00:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Attendence](
	[aid] [int] NOT NULL,
	[wsid] [int] NOT NULL,
	[quantity] [int] NOT NULL,
	[alpha] [float] NOT NULL,
 CONSTRAINT [PK_Attendent] PRIMARY KEY CLUSTERED 
(
	[aid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Dept]    Script Date: 10/30/2024 8:00:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Dept](
	[did] [int] NOT NULL,
	[dname] [nvarchar](50) NOT NULL,
	[type] [nchar](10) NOT NULL,
 CONSTRAINT [PK_Dept] PRIMARY KEY CLUSTERED 
(
	[did] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Employee]    Script Date: 10/30/2024 8:00:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Employee](
	[eid] [int] NOT NULL,
	[ename] [nvarchar](50) NOT NULL,
	[salarylevel] [int] NOT NULL,
	[did] [int] NOT NULL,
 CONSTRAINT [PK_Employee] PRIMARY KEY CLUSTERED 
(
	[eid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Plan]    Script Date: 10/30/2024 8:00:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Plan](
	[plid] [int] NOT NULL,
	[startTime] [date] NOT NULL,
	[endTime] [date] NOT NULL,
	[did] [int] NOT NULL,
 CONSTRAINT [PK_Plan] PRIMARY KEY CLUSTERED 
(
	[plid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PlanCampaign]    Script Date: 10/30/2024 8:00:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PlanCampaign](
	[camid] [int] NOT NULL,
	[plid] [int] NOT NULL,
	[pid] [int] NOT NULL,
	[quantity] [int] NOT NULL,
	[unitEffort] [int] NOT NULL,
 CONSTRAINT [PK_PlanCampaign] PRIMARY KEY CLUSTERED 
(
	[camid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 10/30/2024 8:00:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[pid] [int] NOT NULL,
	[pname] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[pid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ScheduleCampaign]    Script Date: 10/30/2024 8:00:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ScheduleCampaign](
	[scid] [int] NOT NULL,
	[camid] [int] NOT NULL,
	[date] [date] NOT NULL,
	[shift] [nchar](10) NOT NULL,
	[quantity] [int] NOT NULL,
 CONSTRAINT [PK_ScheduleCampaign] PRIMARY KEY CLUSTERED 
(
	[scid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[U_E]    Script Date: 10/30/2024 8:00:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[U_E](
	[uid] [int] NOT NULL,
	[eid] [int] NOT NULL,
	[isActive] [nchar](10) NOT NULL,
 CONSTRAINT [PK_U_E] PRIMARY KEY CLUSTERED 
(
	[uid] ASC,
	[eid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 10/30/2024 8:00:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[uid] [int] NOT NULL,
	[uname] [nchar](10) NOT NULL,
	[password] [nchar](10) NOT NULL,
	[displayName] [nvarchar](10) NOT NULL,
	[isLocked] [nchar](10) NOT NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[uid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[WorkerSchedule]    Script Date: 10/30/2024 8:00:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[WorkerSchedule](
	[wsid] [int] NOT NULL,
	[scid] [int] NOT NULL,
	[eid] [int] NOT NULL,
	[quantity] [int] NOT NULL,
 CONSTRAINT [PK_WorkerSchedule] PRIMARY KEY CLUSTERED 
(
	[wsid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Attendence]  WITH CHECK ADD  CONSTRAINT [FK_Attendent_WorkerSchedule] FOREIGN KEY([wsid])
REFERENCES [dbo].[WorkerSchedule] ([wsid])
GO
ALTER TABLE [dbo].[Attendence] CHECK CONSTRAINT [FK_Attendent_WorkerSchedule]
GO
ALTER TABLE [dbo].[Employee]  WITH CHECK ADD  CONSTRAINT [FK_Employee_Dept] FOREIGN KEY([did])
REFERENCES [dbo].[Dept] ([did])
GO
ALTER TABLE [dbo].[Employee] CHECK CONSTRAINT [FK_Employee_Dept]
GO
ALTER TABLE [dbo].[Plan]  WITH CHECK ADD  CONSTRAINT [FK_Plan_Dept] FOREIGN KEY([did])
REFERENCES [dbo].[Dept] ([did])
GO
ALTER TABLE [dbo].[Plan] CHECK CONSTRAINT [FK_Plan_Dept]
GO
ALTER TABLE [dbo].[PlanCampaign]  WITH CHECK ADD  CONSTRAINT [FK_PlanCampaign_Plan] FOREIGN KEY([plid])
REFERENCES [dbo].[Plan] ([plid])
GO
ALTER TABLE [dbo].[PlanCampaign] CHECK CONSTRAINT [FK_PlanCampaign_Plan]
GO
ALTER TABLE [dbo].[PlanCampaign]  WITH CHECK ADD  CONSTRAINT [FK_PlanCampaign_Product] FOREIGN KEY([pid])
REFERENCES [dbo].[Product] ([pid])
GO
ALTER TABLE [dbo].[PlanCampaign] CHECK CONSTRAINT [FK_PlanCampaign_Product]
GO
ALTER TABLE [dbo].[ScheduleCampaign]  WITH CHECK ADD  CONSTRAINT [FK_ScheduleCampaign_PlanCampaign] FOREIGN KEY([camid])
REFERENCES [dbo].[PlanCampaign] ([camid])
GO
ALTER TABLE [dbo].[ScheduleCampaign] CHECK CONSTRAINT [FK_ScheduleCampaign_PlanCampaign]
GO
ALTER TABLE [dbo].[U_E]  WITH CHECK ADD  CONSTRAINT [FK_U_E_Employee] FOREIGN KEY([eid])
REFERENCES [dbo].[Employee] ([eid])
GO
ALTER TABLE [dbo].[U_E] CHECK CONSTRAINT [FK_U_E_Employee]
GO
ALTER TABLE [dbo].[U_E]  WITH CHECK ADD  CONSTRAINT [FK_U_E_User] FOREIGN KEY([uid])
REFERENCES [dbo].[User] ([uid])
GO
ALTER TABLE [dbo].[U_E] CHECK CONSTRAINT [FK_U_E_User]
GO
ALTER TABLE [dbo].[WorkerSchedule]  WITH CHECK ADD  CONSTRAINT [FK_WorkerSchedule_Employee] FOREIGN KEY([eid])
REFERENCES [dbo].[Employee] ([eid])
GO
ALTER TABLE [dbo].[WorkerSchedule] CHECK CONSTRAINT [FK_WorkerSchedule_Employee]
GO
ALTER TABLE [dbo].[WorkerSchedule]  WITH CHECK ADD  CONSTRAINT [FK_WorkerSchedule_ScheduleCampaign] FOREIGN KEY([scid])
REFERENCES [dbo].[ScheduleCampaign] ([scid])
GO
ALTER TABLE [dbo].[WorkerSchedule] CHECK CONSTRAINT [FK_WorkerSchedule_ScheduleCampaign]
GO
